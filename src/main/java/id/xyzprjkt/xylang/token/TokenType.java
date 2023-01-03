package id.xyzprjkt.xylang.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TokenType {
    Comment("\\#.*"),
    LineBreak("[\\n\\r]"),
    Whitespace("[\\s\\t]"),
    Keyword("(kalo|perhaps|kalogak|udahan|spill|input|class|so|return|fomo|in|by|stop|skip|execute|help|version|credits)(?=\\s|$)"),
    GroupDivider("(\\[|\\]|\\,|\\{|}|[.]{2})"),
    Logical("(true|false)(?=\\s|$)"),
    Numeric("([-]?(?=[.]?[0-9])[0-9]*(?![.]{2})[.]?[0-9]*)"),
    Null("(null)(?=,|\\s|$)"),
    This("(this)(?=,|\\s|$)"),
    Text("\"([^\"]*)\""),
    Operator("(\\+|-|\\*|/{1,2}|%|>=|>|<=|<{1,2}|={1,2}|!=|!|:{2}|\\(|\\)|(new|and|or)(?=\\s|$))"),
    Variable("[a-zA-Z_]+[a-zA-Z0-9_]*");

    private final String regex;
}

