package com.loc.hydrateme.hydrateme.data.repository

import com.loc.hydrateme.hydrateme.data.local.HydrateDao
import com.loc.hydrateme.hydrateme.data.local.dto.DayEntity
import com.loc.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.loc.hydrateme.hydrateme.data.local.dto.UserEntity
import com.loc.hydrateme.hydrateme.data.mapper.*
import com.loc.hydrateme.hydrateme.domain.model.*
import com.loc.hydrateme.hydrateme.domain.repository.HydrateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.*

class HydrateRepositoryImpl(
    private val dao: HydrateDao,
) : HydrateRepository {

    override suspend fun insertUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun insertDay(day: DayEntity) {
        dao.insertDay(day)
    }

    override suspend fun getUserAndDays(): Flow<UserAndDays> {
        return dao.getUserAndDays().map {
            it.toUserAndDays()
        }
    }

    override suspend fun getUser(): Flow<UserEntity> {
        return dao.getUser()
    }

    override suspend fun drink(totalAmount: Int) {
        dao.updateComplete(totalAmount)
    }


    override suspend fun clearDayTable() {
        dao.clearDayTable()
    }

    override suspend fun clearHistoryTable() {
        dao.clearHistoryTable()
    }

    override suspend fun clearAlarmsTable() {
        dao.clearAlarmsTable()
    }

    override suspend fun insertHistory(historyEntity: HistoryEntity) {
        dao.insertHistoryRecord(historyEntity)
    }

    override suspend fun getLastDay(): Flow<Day?> {
        return dao.getLastDay().map {
            it?.toDay()
        }
    }

    override fun getTodayReport(daysDuration: Int): Flow<List<History>> {
        return dao.getReportByDay(daysDuration).map {
            it.first().historyEntity.map {
                it.toHistory()
            }
        }
    }

    override fun get10DaysReport(): Flow<List<History>> {
        return dao.getReportByDay(10).map {
            it.mapIndexed { i, dayEntityWithHistoryEntity ->
                val sum =
                    dayEntityWithHistoryEntity.historyEntity.sumOf { it.drinkAmount } //Sum of day drinks
                var time = 0L
                dayEntityWithHistoryEntity.historyEntity.forEach {
                    time = it.time
                }
                History(time, sum)
            }
        }.map {
            val complementTo10: MutableList<History> = it.toMutableList()
            while (complementTo10.size < 10) {
                complementTo10.add(History(0, 0))
            }
            complementTo10

        }
    }


    override suspend fun getCompletedAmount(day: Long): Flow<List<Int>> {
        return dao.getCompletedAmount(day)
    }

    //How the periodic report function works ?
    //suppose this list [1,2,3,4,5,6,7,8,10,11,12]
    //Now to get a weekly report from this list we need to divide this list into sublists
    //each sublist with size 7 (Because in the week there is 7 days)
    //Now take each sublist and find it's average then save it in the report list (the output list)

    override fun get10WeeksReport(): Flow<List<History>> {
        return dao.getReportByDay(10 * 7)
            .onEach {
                Collections.reverse(it)
            }
            .map {
                var weeks = mutableListOf<History>() //Output list
                val size = it.size
                var weekStep =
                    0 //The shift value, here we will shift by 7 indices because it's a week
                while (weekStep + 7 <= size) {
                    val subList = it.subList(weekStep, weekStep + 7)
                    val day =
                        subList.first().day.day //Get the time of the first day in the sublist and consider (later it will be used to get the week)
                    var sum = 0
                    subList.forEach {
                        sum += it.historyEntity.sumOf { it.drinkAmount }
                    }
                    weeks.add(History(day, sum))
                    sum = 0
                    weekStep += 7
                }
                //Put the extra days into new list
                //example: [1,2,3,4,5,6,7,8,9] the sub lists of this list are [1,2,3,4,5,6,7] and [8,9]
                //this code will make the [8,9] sublist
                if (weekStep < size) {
                    val subList = it.subList(weekStep, size)
                    val day = subList.first().day.day
                    var sum = 0
                    subList.forEach {
                        sum += it.historyEntity.sumOf { it.drinkAmount }
                    }
                    weeks.add(History(day, sum))
                    sum = 0
                }
                weeks.reverse()
                //To make sure that our report has 10 weeks, if the report < 10 days, add empty history
                while (weeks.size < 10) {
                    weeks.add(History(0, 0))
                }
                weeks
            }
    }

    override fun get10MonthsReport(): Flow<List<History>> {
        return dao.getReportByDay(10 * 30)
            .onEach {
                Collections.reverse(it)
            }
            .map {
            val months = mutableListOf<History>()
            val size = it.size
            var weekStep = 0
            while (weekStep + 30 <= size) {
                val subList = it.subList(weekStep, weekStep + 30)
                val day = subList.first().day.day
                var sum = 0
                subList.forEach {
                    sum += it.historyEntity.sumOf { it.drinkAmount }
                }
                months.add(History(day, sum))
                sum = 0
                weekStep += 30
            }
            if (weekStep < size) {
                val subList = it.subList(weekStep, size)
                val day = subList.first().day.day
                var sum = 0
                subList.forEach {
                    sum += it.historyEntity.sumOf { it.drinkAmount }
                }
                months.add(History(day, sum))
            }
            months.reverse()
            while (months.size < 10) {
                months.add(History(0, 0))
            }
            months
        }
    }

    override fun get10YearsReport(): Flow<List<History>> {
        return dao.getReportByDay(10 * 362)
            .onEach {
                Collections.reverse(it)
            }
            .map {
            val years = mutableListOf<History>()
            val size = it.size
            var weekStep = 0
            while (weekStep + 362 <= size) {
                val subList = it.subList(weekStep, weekStep + 362)
                val day = subList.first().day.day
                var sum = 0
                subList.forEach {
                    sum += it.historyEntity.sumOf { it.drinkAmount }
                }
                years.add(History(day, sum))
                sum = 0
                weekStep += 362
            }
            if (weekStep < size) {
                val subList = it.subList(weekStep, size)
                val day = subList.first().day.day
                var sum = 0
                subList.forEach {
                    sum += it.historyEntity.sumOf { it.drinkAmount }
                }
                years.add(History(day, sum))
                sum = 0
            }
            years.reverse()
            while (years.size < 10) {
                years.add(History(0, 0))
            }
            years
        }
    }

    override suspend fun updateCupSize(cupSize: Int) {
        dao.updateCupSize(cupSize)
    }

    override suspend fun insertAlarm(alarm: Alarm) {
        dao.insertAlarm(alarm.toAlarmEntity(0, alarm.id))
    }

    override suspend fun insertAlarms(alarms: List<Alarm>) {
//        dao.insertAlarms(alarms.map { it.toAlarmEntity(1) })
    }

    override suspend fun getAlarms(userId: Int): List<Alarm> {
        return dao.getAlarms(userId).map {
            it.toAlarm()
        }
    }

    override suspend fun insertAlarmDay(alarmDay: AlarmDay, alarmId: Int) {
        dao.insertAlarmDay(alarmDay.toAlarmDayEntity(alarmId))
    }

    override suspend fun getAlarmsAsFlow(): Flow<List<Alarm>> {
        return dao.getAlarmsAsFlow().map { it.map { it.toAlarm() } }
    }

    override suspend fun clearUserTable() {
        dao.clearUserTable()
    }

    override suspend fun getNotificationSound(): Int {
        return dao.getNotificationSound()
    }
}