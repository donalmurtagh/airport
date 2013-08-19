package com.icaviation


class ToolboxSection {

    String heading
    String text
    Boolean showCheckboxes = true

    static hasMany = [toolboxItems: ToolboxItem]
    List toolboxItems

    static belongsTo = [toolbox: Toolbox]

    static constraints = {
        heading blank: false
        text blank: false
    }

    static mapping = {
        text type: 'text'

        cache usage: 'read-only'
        toolboxItems cache: true
    }
}
