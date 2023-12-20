package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.KingBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A2));
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException() {
        Cell first = Cell.A8;
        Cell second = Cell.G1;
        Logic  logic = new Logic();
        logic.add(new BishopBlack(first));
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(first, second);
        });
        assertThat(exception.getMessage()).isEqualTo("Could "
                + "not way by diagonal from %s to %s", first, second);
    }

    @Test
    public void whenMoveThenOccupiedCellException() {
        Cell firstBB = Cell.B7;
        Cell secondBB = Cell.G2;
        Cell firstKB = Cell.D5;
        Logic  logic = new Logic();
        logic.add(new BishopBlack(firstBB));
        logic.add(new KingBlack(firstKB));
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(firstBB, secondBB);
        });
        assertThat(exception.getMessage()).isEqualTo("Cell is occupied");
    }
}