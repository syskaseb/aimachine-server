package ai.aimachineserver.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

class Board(
    private val allFieldValues: Array<IntArray> = Array(BOARD_SIZE) { IntArray(BOARD_SIZE) { BLANK_VALUE } }
) {
    private companion object {
        const val BOARD_SIZE = 3
        const val BLANK_VALUE = 0
    }

    fun getAllFieldValues() = allFieldValues

    fun setFieldValue(rowIndex: Int, colIndex: Int, fieldValue: Int) {
        allFieldValues[rowIndex][colIndex] = fieldValue
    }

    fun getAvailableFieldIndices(): List<Pair<Int, Int>> {
        val availableFieldIndices = mutableListOf<Pair<Int, Int>>()
        allFieldValues.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, fieldValue ->
                if (fieldValue == BLANK_VALUE) {
                    availableFieldIndices.add(Pair(rowIndex, colIndex))
                }
            }
        }
        return availableFieldIndices
    }

    fun isFieldAvailable(rowIndex: Int, colIndex: Int) = allFieldValues[rowIndex][colIndex] == BLANK_VALUE

    fun clearAllFields() = allFieldValues.forEachIndexed { rowIndex, row ->
        row.indices.forEach { colIndex ->
            allFieldValues[rowIndex][colIndex] = BLANK_VALUE
        }
    }
}

@SpringBootTest
class BoardTest {

    @Test
    fun init_noValuePassed_allFieldsBlank() {
        val board = Board()
        assertThat(board.getAllFieldValues()).isEqualTo(Array(3) { IntArray(3) { 0 } })
    }
}
