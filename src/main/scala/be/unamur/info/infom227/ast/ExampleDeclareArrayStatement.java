// File: `src/main/java/be/unamur/info/infom227/ast/ExampleDeclareArrayStatement.java`
package be.unamur.info.infom227.ast;

public class ExampleDeclareArrayStatement extends ExampleStatement {
    private final int line;
    private final String name;
    private final ExampleType elementType;
    private final int size;

    public ExampleDeclareArrayStatement(int line, String name, ExampleType elementType, int size) {
        this.line = line;
        this.name = name;
        this.elementType = elementType;
        this.size = size;
    }

    public int getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public ExampleType getElementType() {
        return elementType;
    }

    public int getSize() {
        return size;
    }
}