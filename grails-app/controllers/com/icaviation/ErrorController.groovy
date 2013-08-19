package com.icaviation


class ErrorController {

    def forbidden() {
        handleError()
    }

    def notFound() {
        handleError()
    }

    def serverError() {
        handleError()
    }

    private handleError() {
        flashHelper.warn "error.${actionName}"
        redirect uri: '/'
    }
}
