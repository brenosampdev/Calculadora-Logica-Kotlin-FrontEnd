package data

data class ResponseTable(
    val assignments: List<Map<String, Boolean>>,
    val results: List<Boolean>,
    val classify: ClassificationTable
)

