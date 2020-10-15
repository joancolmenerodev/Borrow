package com.borrow.librarybase.exceptions

class SomethingWentWrongException(override val cause: Exception? = null) : Exception(cause)