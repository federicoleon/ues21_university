import com.ues21.marshallers.*

beans = {

    customObjectMarshallers(CustomObjectMarshallers) { bean ->
        bean.scope = "singleton"
        marshallers = [
            new CareerMarshaller(),
            new CareerPlanMarshaller(),
            new SubjectMarshaller()
        ]
    }
}
