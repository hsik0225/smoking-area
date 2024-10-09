package com.hsik.smoking.common

open class HumanException(
    message: String,
) : RuntimeException(message)

class ResourceNotFoundException(
    message: String,
) : HumanException(message) {
    constructor(id: String, resource: String) : this("$[ID: $id]를 가진 $resource 가 존재하지 않습니다.")
}
