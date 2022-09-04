package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.domain.model.Alarm
import com.example.hydrateme.hydrateme.domain.model.AlarmDay
import com.example.hydrateme.hydrateme.domain.repository.HydrateRepository
import com.example.hydrateme.util.getWeekDaysCalender
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
            hydrateRepository.insertAlarm(Alarm(alarm.hour,
                alarm.minute,
                getWeekDaysCalender().map { it.timeInMillis },
                true),
                index
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
        val wakeUpTimeArray = wakeUpTime.split(":").toTypedArray()
        val sleepTimeArray = sleepTime.split(":").toTypedArray()

        val wakeUpHour = wakeUpTimeArray[0].toInt()
        val wakeUpMinute = wakeUpTimeArray[1].toInt()
        val sleepHour = sleepTimeArray[0].toInt()
        val sleepMinute = sleepTimeArray[1].toInt()

        val hourDifference = Math.abs(sleepHour - wakeUpHour)
        val minuteDifference = Math.abs(sleepMinute - wakeUpMinute)

        val availableHours = hourDifference + minuteDifference / 60f

        val alarmsCount = (target / drinkAmount).toFloat()

        val rangeInMinutes = availableHours / alarmsCount * 60

        val wakeUpInMinutes = wakeUpHour * 60 + wakeUpMinute
        val sleepInMinutes = sleepHour * 60 + sleepMinute

        var initialAlarm = wakeUpInMinutes - (rangeInMinutes / 2).toInt()
        val alarms: ArrayList<Int> = ArrayList()

        while (initialAlarm + Math.round(rangeInMinutes) < sleepInMinutes) {
            alarms.add(initialAlarm + Math.round(rangeInMinutes))
            initialAlarm += Math.round(rangeInMinutes)
        }

        val daysTimeStamp = getWeekDaysCalender().map { it.timeInMillis }
        return alarms.map {
            Alarm(it/60,it%60,daysTimeStamp,true)
        }
    }
}