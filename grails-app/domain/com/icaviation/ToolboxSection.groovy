package com.icaviation


class ToolboxSection {

    String name

    static hasMany = [toolboxItems: ToolboxItem]
    List toolboxItems

    static belongsTo = [toolbox: Toolbox]

    static constraints = {
        name blank: false
    }

    static mapping = {
        cache usage: 'read-only'
        toolboxItems cache: true
    }
}
