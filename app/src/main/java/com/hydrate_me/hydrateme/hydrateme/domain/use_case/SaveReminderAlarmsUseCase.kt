package com.hydrate_me.hydrateme.hydrateme.domain.use_case

import com.hydrate_me.hydrateme.hydrateme.domain.model.Alarm
import com.hydrate_me.hydrateme.hydrateme.domain.model.AlarmDay
import com.hydrate_me.hydrateme.hydrateme.domain.repository.HydrateRepository
import java.util.*

class SaveReminderAlarmsUseCase(
    private val hydrateRepository: HydrateRepository,
) {

    suspend operator fun invoke(
        wakeUpTime: String,
        sleepTime: String,
        drinkAmount: Int,
        target: Int,
    ) {
        val alarms = getAlarmsList(wakeUpTime, sleepTime, drinkAmount, target)
        insertAlarms(alarms)
    }


    private suspend fun insertAlarms(alarms: List<Alarm>) {
        val weekDaysCalender = getWeekDaysCalender()
        alarms.forEachIndexed { index, alarm ->
            hydrateRepository.insertAlarm(Alarm(
                alarm.id,
                alarm.hour,
                alarm.minute,
                getWeekDaysCalender().map { it.timeInMillis },
                true)
            )
            weekDaysCalender.forEach {
                hydrateRepository.insertAlarmDay(AlarmDay(it.timeInMillis), index)
            }
        }
    }

    private fun getAlarmsList(
        wakeUpTime: String,
        sleepTime: String,
        drinkAmount: Int,
        target: Int,
    ): List<Alarm> {

        //Convert the HH:MM to array of {HH,MM} for both wakeup time and sleep time.
        val wakeUpTimeArray = wakeUpTime.split(":").toTypedArray()
        val sleepTimeArray = sleepTime.split(":").toTypedArray()

        //The hour will be at index 0 and the minuit at index 1.
        val wakeUpHour = wakeUpTimeArray[0].toInt()
        val wakeUpMinute = wakeUpTimeArray[1].toInt()
        val sleepHour = sleepTimeArray[0].toInt()
        val sleepMinute = sleepTimeArray[1].toInt()

        //Find the difference between the wakeup hour and the sleep hour (Used later to find the Available hours).
        // The formula is : availableHours = (sleepHour - wakeUpHour) + (sleepMinute - wakeUpMinutes) / 60f.
        val hourDifference = findHourDifference(wakeUpHour, sleepHour)
        val minuteDifference = Math.abs(sleepMinute - wakeUpMinute)

        //Convert the minutes to hours and add them to the hourDifference to get the available hours (number of hours when the user is active).
        val availableHours = hourDifference + minuteDifference / 60f

        //Number of alarms should be sent per day from the wakeUpTime until the sleepTime.
        val alarmsCount = (target / drinkAmount).toFloat()

        //The time difference in minutes between each alarm and the next alarm.
        val alarmDifferenceInMinutes = availableHours / alarmsCount * 60

        // Convert to minutes.
        val wakeUpInMinutes = wakeUpHour * 60 + wakeUpMinute
        var sleepInMinutes = sleepMinute
        //To make solve the issue when the sleep hour in the range between 1 to 12 am. for example of the sleepHour is 1 am,
        // if we convert it to minutes by multiplying by 60 we will get 60 minutes which is wrong.
        if (sleepHour in 1..12) {
            sleepInMinutes += (24 + sleepHour) * 60
        } else {
            sleepInMinutes = sleepHour * 60
        }
        //Find the the initial alarm time in minutes.
        var initialAlarm = wakeUpInMinutes - (alarmDifferenceInMinutes / 2).toInt()
        //List of the alarms times in minutes that will be send to the user.
        val alarms: ArrayList<Int> = ArrayList()

        //Loop from the initial alarm time until the sleep time and at each iteration add the the alarm difference then add it to the list.
        while (initialAlarm + Math.round(alarmDifferenceInMinutes) < sleepInMinutes) {
            alarms.add(initialAlarm + Math.round(alarmDifferenceInMinutes))
            initialAlarm += Math.round(alarmDifferenceInMinutes)
        }

        // Get the week days from sun-sat in in millis.
        val daysTimeStamp = getWeekDaysCalender().map { it.timeInMillis }

        //convert the alarms list into Alarm objects.
        return alarms.mapIndexed { index, item->
            val hour = item / 60
            val minutes = item % 60
            Alarm(index,if (hour > 24) hour - 24 else hour, minutes, daysTimeStamp, true)
        }
    }

    //Finding the hourDifference by a loop starts from the wakeup time until reaching the sleepTime.
    //Used a loop because the case of : wakeup time 6:00 and sleep time 5:00
    private fun findHourDifference(wakeUpHour: Int, sleepHour: Int): Int {
        var wakeUpTemp = wakeUpHour
        var hourDifference = 0
        while (wakeUpTemp != sleepHour) {
            if (wakeUpTemp == 24) wakeUpTemp = 0
            wakeUpTemp++
            hourDifference++
        }
        return hourDifference
    }

    private fun getWeekDaysCalender(): List<Calendar> {
        val sunday = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        }
        val monday = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        }
        val tuesday = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY)
        }
        val wednesday = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
        }
        val thursday = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY)
        }
        val friday = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
        }
        val saturday = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        }

        return listOf(sunday,
            monday,
            tuesday,
            wednesday,
            thursday,
            friday,
            saturday)
    }
}