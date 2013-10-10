package com.icaviation

class Toolbox {
    String name
    String introHeading
    String introText

    static hasMany = [toolboxSections: ToolboxSection]
    List toolboxSections

    static constraints = {
        name blank: false
        introHeading nullable: true
        introText nullable: true
    }

    static mapping = {
        cache usage: 'read-only'
        toolboxSections cache: true
        introText type: 'text'
    }
}
