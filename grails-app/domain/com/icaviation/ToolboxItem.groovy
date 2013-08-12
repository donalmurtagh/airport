package com.icaviation


class ToolboxItem {
    String heading
    String text

    static hasMany = [responses: Response]
    static belongsTo = [toolbox: Toolbox]

    static constraints = {
        heading blank: false
        text blank: false
    }

    static mapping = {
        cache usage: 'read-only'
        text type: 'text'
    }
}
