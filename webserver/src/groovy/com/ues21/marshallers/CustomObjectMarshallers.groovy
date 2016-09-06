package com.ues21.marshallers

class CustomObjectMarshallers {
    List marshallers = []

    def register() {
        marshallers.each{ marshaller ->
            marshaller.register()
        }
    }
}
