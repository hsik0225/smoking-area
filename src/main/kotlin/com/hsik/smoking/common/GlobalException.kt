package com.hsik.smoking.common

open class HumanException(
    message: String,
) : RuntimeException(message)

class ResourceNotFoundException(
    message: String,
) : HumanException(message) {
    constructor(
        fieldName: String,
        fieldValue: String,
        resource: String,
    ) : this("[$fieldName: $fieldValue]를 가진 $resource 가 존재하지 않습니다.")
}

open class CommunicationException(
    message: String,
    val additionalInformation: Map<String, Any>? = null,
) : RuntimeException(message)
