package com.icaviation


class Toolbox {
    String name
    static hasMany = [toolboxItems: ToolboxItem]

    static constraints = {
        name blank: false
    }

    static mapping = {
        cache usage: 'read-only'
        toolboxItems cache: true
    }
}
