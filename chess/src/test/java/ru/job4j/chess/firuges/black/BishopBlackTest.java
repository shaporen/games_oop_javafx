package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    public void whenPositionIsCorrect() {
        Cell first = Cell.A1;
        BishopBlack bishopBlack = new BishopBlack(first);
        Cell pos = bishopBlack.position();
        assertThat(pos).isEqualTo(first);
    }

    @Test
    public void whenCopyIsCorrect() {
        Cell first = Cell.A1;
        BishopBlack bishopBlack = new BishopBlack(first);
        Figure copy = bishopBlack.copy(first);
        assertThat(copy.position()).isEqualTo(bishopBlack.position());
    }

    @Test
    public void whenIsDiagonal() {
        Cell first = Cell.A7;
        BishopBlack bishopBlack = new BishopBlack(first);
        Cell second = Cell.E3;
        boolean result = bishopBlack.isDiagonal(first, second);
        assertThat(result).isTrue();
    }

    @Test
    public void whenIsNotDiagonal() {
        Cell first = Cell.A7;
        BishopBlack bishopBlack = new BishopBlack(first);
        Cell second = Cell.E5;
        boolean result = bishopBlack.isDiagonal(first, second);
        assertThat(result).isFalse();
    }

    @Test
    public void whenWayIsCorrect() {
        Cell first = Cell.A7;
        BishopBlack bishopBlack = new BishopBlack(first);
        Cell second = Cell.G1;
        Cell[] result = bishopBlack.way(second);
        assertThat(result).containsExactly(new Cell[]{Cell.B6, Cell.C5,
                Cell.D4, Cell.E3, Cell.F2, Cell.G1});
    }

    @Test
    public void whenWayIsNotCorrect() {
        Cell first = Cell.A7;
        BishopBlack bishopBlack = new BishopBlack(first);
        Cell second = Cell.F1;
        ImpossibleMoveException exception = assertThrows(
            ImpossibleMoveException.class, () -> bishopBlack.way(second)
        );
        assertThat(exception.getMessage()).isEqualTo(
                "Could not way by diagonal from %s to %s", first, second);
    }
}