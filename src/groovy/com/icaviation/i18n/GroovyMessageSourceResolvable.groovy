package com.icaviation.i18n

import org.springframework.context.MessageSourceResolvable

import static java.util.Collections.emptyList
import static java.util.Collections.singletonList

class GroovyMessageSourceResolvable implements MessageSourceResolvable {

    private String[] codesArray
    private Object[] argsArray

    final String defaultMessage

    private static final String[] EMPTY_STRING_ARRAY = emptyList().toArray()

    /**
     *
     * @param codes a String or List<String> of codes used to resolve the message
     * @param args [optional] one or more message arguments
     * @param defaultMsg [optional] message to be used if resolution fails with <tt>codes</tt> and <tt>args</tt>
     */
    GroovyMessageSourceResolvable(def codes, List<Object> args = emptyList(), defaultMsg = null) {

        if (codes instanceof String) {
            codes = singletonList(codes)
        }

        // pass a String[] param to ensure the List is converted a String[] rather than an Object[]
        this.codesArray = codes.toArray(EMPTY_STRING_ARRAY)
        this.argsArray = args.toArray()
        defaultMessage = defaultMsg
    }

    String[] getCodes() {
        codesArray
    }

    Object[] getArguments() {
        argsArray
    }
}
