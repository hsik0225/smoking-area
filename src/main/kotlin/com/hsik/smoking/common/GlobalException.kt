package com.hsik.smoking.common

open class HumanException(
    message: String,
) : RuntimeException(message)

class ResourceNotFoundException(
    id: String,
    resource: String,
) : HumanException("$[ID: $id]를 가진 $resource 가 존재하지 않습니다.")
