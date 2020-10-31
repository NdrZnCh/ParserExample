package parser

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ArithmeticParserTest {

    @Test
    fun parse1() {
        assertEquals(ArithmeticParser("2 + 60").parse().evaluate().toDouble(), 62.00)
    }

    @Test
    fun parse2() {
        assertEquals(ArithmeticParser("2 + 6 * 7 + 10 / 5 + 50 - 7 / 7").parse().evaluate().toDouble(), 95.00)
    }

    @Test
    fun parse3() {
        assertEquals(ArithmeticParser("2 * 6").parse().evaluate().toDouble(), 12.00)
    }

    @Test
    fun parse4() {
        assertEquals(ArithmeticParser("6 / 2").parse().evaluate().toDouble(), 3.00)
    }

    @Test
    fun parse5() {
        assertEquals(ArithmeticParser("1 + 2 * 3").parse().evaluate().toDouble(), 7.00)
    }

    @Test
    fun parse6() {
        assertEquals(ArithmeticParser("2 * 3 + 1").parse().evaluate().toDouble(), 7.00)
    }

    @Test
    fun parse7() {
        assertEquals(ArithmeticParser("2 * 3 + 1 / 2 + 1").parse().evaluate().toDouble(), 7.50)
    }

    @Test
    fun parse8() {
        assertEquals(ArithmeticParser("6 * 2 * 3 + 1").parse().evaluate().toDouble(), 37.00)
    }

    @Test
    fun parse9() {
        assertEquals(ArithmeticParser("6 * 2 * (3 + 1)").parse().evaluate().toDouble(), 48.00)
    }

    @Test
    fun parse10() {
        assertEquals(ArithmeticParser("(6 * 2) * 3 + 1").parse().evaluate().toDouble(), 37.00)
    }

    @Test
    fun parse11() {
        assertEquals(ArithmeticParser("(6 * 2) * (3 + 1)").parse().evaluate().toDouble(), 48.00)
    }
}