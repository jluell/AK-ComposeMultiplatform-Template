package shared.domain.usecase

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.plus

/**
 * Use case for validating event dates.
 * 
 * Events must occur on the 4th Thursday of each month.
 */
object ValidateEventDate {
    /**
     * Checks if a date is the 4th Thursday of the month.
     * 
     * @param date Date to validate
     * @return true if the date is the 4th Thursday, false otherwise
     */
    fun isFourthThursday(date: LocalDate): Boolean {
        // Check if it's a Thursday
        if (date.dayOfWeek != DayOfWeek.THURSDAY) {
            return false
        }
        
        // Check if it's the 4th Thursday
        // The 4th Thursday must be between day 22 and 28
        val dayOfMonth = date.dayOfMonth
        return dayOfMonth in 22..28
    }
    
    /**
     * Finds the 4th Thursday of a given month and year.
     * 
     * @param year Year
     * @param month Month (1-12)
     * @return LocalDate of the 4th Thursday
     */
    fun findFourthThursday(year: Int, month: Int): LocalDate {
        // Start from the 22nd (earliest possible 4th Thursday)
        var date = LocalDate(year, month, 22)
        
        // Find the first Thursday
        while (date.dayOfWeek != DayOfWeek.THURSDAY) {
            date = date.plus(DatePeriod(days = 1))
        }
        
        // This is the 4th Thursday
        return date
    }
    
    /**
     * Validates that a date is the 4th Thursday and throws if not.
     * 
     * @param date Date to validate
     * @throws IllegalArgumentException if date is not the 4th Thursday
     */
    fun validate(date: LocalDate) {
        require(isFourthThursday(date)) {
            "Event date must be the 4th Thursday of the month. " +
            "Date provided: $date (${date.dayOfWeek})"
        }
    }
}

