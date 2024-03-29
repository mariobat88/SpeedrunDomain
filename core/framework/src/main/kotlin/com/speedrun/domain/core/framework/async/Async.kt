package com.speedrun.domain.core.framework.async

/**
 * The T generic is unused for some classes but since it is sealed and useful for Success and Fail,
 * it should be on all of them.
 *
 * Complete: Success, Fail
 * ShouldLoad: Uninitialized, Fail
 */
sealed class Async<out T>(val complete: Boolean, val shouldLoad: Boolean, private val value: T?) {

    /**
     * Returns the value or null.
     *
     * Success always have a value. Loading and Fail can also return a value which is useful for
     * pagination or progressive data loading.
     *
     * Can be invoked as an operator like: `yourProp()`
     */
    open operator fun invoke(): T? = value
    fun isSuccess() = value != null
}

object Uninitialized : Async<Nothing>(complete = false, shouldLoad = true, value = null), Incomplete
data class Loading<out T>(val value: T? = null) : Async<T>(complete = false, shouldLoad = false, value = value),
    Incomplete

data class Success<out T>(val value: T) : Async<T>(complete = true, shouldLoad = false, value = value) {
    override operator fun invoke(): T = value
}

data class Fail<out T>(
    val title: String? = null,
    val message: String? = null,
    val error: Throwable,
) : Async<T>(complete = true, shouldLoad = true, value = null) {
    override fun equals(other: Any?): Boolean {
        if (other !is Fail<*>) return false

        val otherError = other.error
        return title == other.title &&
                message == other.message &&
                error::class == otherError::class &&
                error.message == otherError.message &&
                error.stackTrace.firstOrNull() == otherError.stackTrace.firstOrNull()
    }

    override fun hashCode(): Int =
        arrayOf(error::class, error.message, error.stackTrace.firstOrNull()).contentHashCode()
}

/**
 * Helper interface for using Async in a when clause for handling both Uninitialized and Loading.
 *
 * With this, you can do:
 * when (data) {
 *     is Incomplete -> Unit
 *     is Success    -> Unit
 *     is Fail       -> Unit
 * }
 */
interface Incomplete
