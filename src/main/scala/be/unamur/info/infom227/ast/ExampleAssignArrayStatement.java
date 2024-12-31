// File: `src/main/java/be/unamur/info/infom227/ast/ExampleAssignArrayStatement.java`
package be.unamur.info.infom227.ast;

public class ExampleAssignArrayStatement extends ExampleStatement {
    private final int line;
    private final String arrayName;
    private final int index;
    private final ExampleExpression value;

    public ExampleAssignArrayStatement(int line, String arrayName, int index, ExampleExpression value) {
        this.line = line;
        this.arrayName = arrayName;
        this.index = index;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public String getArrayName() {
        return arrayName;
    }

    public int getIndex() {
        return index;
    }

    public ExampleExpression getValue() {
        return value;
    }
}