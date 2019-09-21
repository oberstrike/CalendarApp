package main.com.calendarapp.data.local

import java.io.File

//Interface to save/open File
interface FileManager
{
    fun getContent():String?

    fun appendContent(content:String)

    fun reset()
}