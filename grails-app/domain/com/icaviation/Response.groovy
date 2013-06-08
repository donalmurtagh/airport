package com.icaviation


class Response {

    static belongsTo = [toolboxItem: ToolboxItem, user: User]

    boolean complete = true
}
