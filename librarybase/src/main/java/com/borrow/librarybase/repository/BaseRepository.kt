package com.borrow.librarybase.repository

import com.borrow.librarybase.exceptions.ServerException
import com.borrow.librarybase.exceptions.ServiceException
import com.borrow.librarybase.exceptions.SomethingWentWrongException

abstract class BaseRepository {

    inline fun <T> execute(block: () -> T): T =
        try {
            block()
        } catch (error: ServiceException) {
            when (error) {
                is ServerException.ServiceUnavailable -> throw SomethingWentWrongException(error)
                else -> throw error
            }
        }
}