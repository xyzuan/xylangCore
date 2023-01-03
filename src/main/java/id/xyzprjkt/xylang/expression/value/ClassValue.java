package id.xyzprjkt.xylang.expression.value;

import id.xyzprjkt.xylang.context.MemoryContext;
import id.xyzprjkt.xylang.context.MemoryScope;
import id.xyzprjkt.xylang.context.definition.ClassDefinition;
import lombok.Getter;

import java.util.Iterator;
import java.util.stream.Collectors;

@Getter
public class ClassValue extends IterableValue<ClassDefinition> {
    private final MemoryScope memoryScope;

    public ClassValue(ClassDefinition definition, MemoryScope memoryScope) {
        super(definition);
        this.memoryScope = memoryScope;
    }

    @Override
    public String toString() {
        MemoryContext.pushScope(memoryScope);
        try {
            return getValue().getArguments().stream()
                    .map(t -> t + " = " + getValue(t))
                    .collect(Collectors.joining(", ", getValue().getName() + " [ ", " ]"));
        } finally {
            MemoryContext.endScope();
        }
    }

    public Value<?> getValue(String name) {
        MemoryContext.pushScope(memoryScope);
        try {
            Value<?> result = MemoryContext.getScope().getLocal(name);
            return result != null ? result : NullValue.NULL_INSTANCE;
        } finally {
            MemoryContext.endScope();
        }
    }

    public void setValue(String name, Value<?> value) {
        MemoryContext.pushScope(memoryScope);
        try {
            MemoryContext.getScope().setLocal(name, value);
        } finally {
            MemoryContext.endScope();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        ClassValue oValue = (ClassValue) o;

        return getValue()
                .getArguments()
                .stream()
                .allMatch(e -> getValue(e).equals(oValue.getValue(e)));
    }

    @Override
    public Iterator<Value<?>> iterator() {
        return getValue()
                .getArguments()
                .stream()
                .<Value<?>>map(this::getValue)
                .iterator();
    }
}
