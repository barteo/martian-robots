package com.redbadger.robots

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MarsSurfaceDimensionMessageTest {
    @Test
    fun decodeShouldThrowExceptionWhenParseError() {
        // TODO make complete list of parsing errors
        assertFailsWith(ParseException::class) {
            MarsSurfaceDimensionMessage.decode("")
        }
    }

    @Test
    fun decodeShouldReturnObjectWhenParseSucceeded() {
        val test = MarsSurfaceDimensionMessage.decode("5 3\n")
        assertEquals(
            test,
            MarsSurfaceDimension(
                eastWestSize = 5,
                southNorthSize = 3
            )
        )
    }
}

class RobotInputMessageTest {
    @Test
    fun decodeShouldThrowExceptionWhenParseError() {
        // TODO make complete list of parsing errors
        assertFailsWith(ParseException::class) {
            RobotInputMessage.decode("")
        }
    }

    @Test
    fun decodeShouldReturnObjectWhenParseSucceeded() {
        val test = RobotInputMessage.decode("1 1 E\nRFRFRFRF\n")
        assertEquals(
            test,
            RobotInput(
                initialPosition = RobotPosition(
                    x = 1,
                    y = 1,
                    orientation = Orientation.EAST
                ),
                instructions = listOf(
                    RobotInstruction.RIGHT,
                    RobotInstruction.FORWARD,
                    RobotInstruction.RIGHT,
                    RobotInstruction.FORWARD,
                    RobotInstruction.RIGHT,
                    RobotInstruction.FORWARD,
                    RobotInstruction.RIGHT,
                    RobotInstruction.FORWARD
                )
            )
        )
    }
}

class RobotOutputMessageTest {
    @Test
    fun encodeShouldReturnString() {
        val test = RobotOutputMessage.encode(
            RobotOutput(
                finalPosition = RobotPosition(
                    x = 1,
                    y = 1,
                    orientation = Orientation.EAST
                )
            )
        )
        assertEquals(test, "1 1 E")
    }
}
