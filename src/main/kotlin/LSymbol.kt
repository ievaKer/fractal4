import java.lang.Exception

enum class LSymbol(val symbol: Char, val value: List<LSymbol>) {
    P('+', listOf(P)),
    M('-', listOf(M)),
    F('F', listOf(F)),
    I('I', listOf(I)),
    L('L', listOf(P, F, M, F, M, F, P)),
    R('R', listOf(M, F, P, F, P, F, M)),
    S('S', listOf(F, F, P, F, P, F, F, M, F, M, F, F)),
    Z('Z', listOf(F, F, M, F, M, F, F, P, F, P, F, F)),
    BO('[', listOf(BO)),
    BC(']', listOf(BC)),
    OTHER('?', listOf());

    companion object {
        @Suppress("MemberVisibilityCanBePrivate")
        fun bySymbol(s: Char): LSymbol =
            when (s) {
                '+' -> P
                '-' -> M
                '[' -> BO
                ']' -> BC
                'F', 'I', 'L', 'R', 'S', 'Z' -> valueOf(s.toString())
                else -> OTHER
            }

        fun parse(rule: String): List<LSymbol> {
            return rule.map { bySymbol(it) }
        }
    }
}