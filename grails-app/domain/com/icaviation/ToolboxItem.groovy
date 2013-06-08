package com.icaviation


class ToolboxItem {
    Integer page
    String text

    static hasMany = [responses: Response]

    static constraints = {
        text blank: false
    }

    static mapping = {
        cache true
    }
}
