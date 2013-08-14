package com.icaviation


class Toolbox {
    String name

    static hasMany = [toolboxSections: ToolboxSection]
    List toolboxSections

    static constraints = {
        name blank: false
    }

    static mapping = {
        cache usage: 'read-only'
        toolboxSections cache: true
    }
}
