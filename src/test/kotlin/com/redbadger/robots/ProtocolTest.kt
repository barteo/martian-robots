package com.redbadger.robots

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MarsSurfaceDimensionMessageTest {
    @Test
    fun decodeShouldThrowExceptionWhenParseError() {
        // TODO make complete list of parsing errors
        assertFailsWith(ParseException::class) {
            MarsSurfaceDimensionsMessage.decode("")
        }
    }

    @Test
    fun decodeShouldReturnObjectWhenParseSucceeded() {
        val test = MarsSurfaceDimensionsMessage.decode("5 3\n")
        assertEquals(
            MarsSurfaceDimensions(
                southNorthSize = 5,
                eastWestSize = 3
            ),
            test
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
            ),
            test
        )
    }
}

class RobotOutputMessageTest {
    @Test
    fun encodeShouldReturnLostStringWhenStatusLost() {
        val test = RobotOutputMessage.encode(
            RobotOutput(
                finalPosition = RobotPosition(
                    x = 1,
                    y = 1,
                    orientation = Orientation.EAST
                ),
                status = RobotStatus.LOST
            )
        )
        assertEquals("1 1 LOST", test)
    }

    @Test
    fun encodeShouldReturnRespondingStringWhenStatusResponding() {
        val test = RobotOutputMessage.encode(
            RobotOutput(
                finalPosition = RobotPosition(
                    x = 1,
                    y = 1,
                    orientation = Orientation.EAST
                ),
                status = RobotStatus.RESPONDING
            )
        )
        assertEquals("1 1", test)
    }
}
