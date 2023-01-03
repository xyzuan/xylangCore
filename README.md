## xylang Programming Language

![about.png](asset%2Fabout.png)

Based on: [Building Your Own Programming Language From Scratch](https://hackernoon.com/building-your-own-programming-language-from-scratch)

### [Examples .xy Codes](sample)

## How to Compile
```
# Make sure your environtment have a Maven installed
mvn package
mvn compile
```

## Syntax Usages

### Operators

To calculate a complex expression in the proper order, each of the supported operators has its own precedence:

| Operator               | Value | Precedence | Example                  |
|------------------------|-------|------------|--------------------------|
| Assignment             | `=`   | 1          | `a = 5`                  |
| Append value to array  | `<<`  | 1          | `array << "value"`       |
| Logical OR             | `or`  | 2          | `true or false`          |
| Logical AND            | `and` | 3          | `true and true`          |
| Left Paren             | `(`   | 4          |                          |
| Right Paren            | `)`   | 4          |                          |
| Equals                 | `==`  | 5          | `a == 5`                 |
| Not Equals             | `!=`  | 5          | `a != 5`                 |
| Greater Than Or Equals | `>=`  | 5          | `a >= 5`                 |
| Greater Than           | `>`   | 5          | `a > 5`                  |
| Less Than Or Equals    | `<=`  | 5          | `a <= 5`                 |
| Less Than              | `<`   | 5          | `a < 5`                  |
| Addition               | `+`   | 6          | `a + 5`                  |
| Subtraction            | `-`   | 6          | `a - 5`                  |
| Multiplication         | `*`   | 7          | `a * 5`                  |
| Division               | `/`   | 7          | `a / 5`                  |
| Floor Division         | `//`  | 7          | `a // 5`                 |
| Modulo                 | `%`   | 7          | `a % 5`                  |
| NOT                    | `!`   | 8          | `!false`                 |
| Class Instance         | `new` | 8          | `a = new TreeNode [ 5 ]` |
| Class Property         | `::`  | 8          | `b = a :: value`         |

### General Functions
To show a general output :

| Commands               | Function                                                      |
| ---------------------- | ------------------------------------------------------------- |
| help                   | Used to see documentation and usages                          |
| version                | Used to see xyLang version are installed on this machine      |

### Basic constructions

1. Variables declaration

```
# Plain types
<variable name> = <expression>

a = 1
b = "Hemlo Indonesia"

# Class instance
<variable name> = new <class name> [ <argument expression 1>, <argument expression 2>, ... ]

left_tree_node = new TreeNode [ 1 ]
right_tree_node = new TreeNode [ 2 ]
tree_node = new TreeNode [ 3, left_tree_node, right_tree_node ]
tree_node = new TreeNode [ 3, new TreeNode [ 1 ], null ]

# Arrays
<array> = { <value1>, <value2>, ... }
example_array = { 1, 2, "three", new TreeNode [ 4 ] }
empty_array = {}
```

2. Conditions

```
kalo <condition>
    # Block Statement
perhaps <condition>
    # Block Statement
kalogak
    # Block Statement
udahan

kalo a > 5 and tree_node :: value == 3
    # Block Statement
perhaps b == "hello" or c == "world"
    # Block Statement
kalogak
    # Block Statement
udahan
```

3. Print to console

```
spill <expression>
spill a + b + tree_node :: value
```

4. Input from console

```
input <variable name>
input number
```

5. Functions

```
so <function name> [ <argument1, argument2>, ... ]
    <body>
    return <expression>
udahan

so fibonacci_number [ n ]
    kalo n < 2
        return n
    udahan
    return fibonacci_number [ n - 1 ] + fibonacci_number [ n - 2 ]
udahan
```

6. Loops

```
# For loop
fomo <variable> in <lower_bound>..<upper_bound>
    # statements
udahan

# Specify the step
fomo <variable> in <lower_bound>..<upper_bound> by <step>
    # statements
    # seed increment statement
udahan

# While loop
fomo <condition>
    # statements
udahan

# Iterable loop (for-each)
fomo <variable> in <iterable>
    # statements
udahan

# terminate the loop
fomo <variable> in <lower_bound>..<upper_bound> by <step>
    kalo <other_condition>
        stop
    udahan
udahan

# jump to the next iteration
fomo <variable> in <lower_bound>..<upper_bound> by <step>
    kalo <other_condition>
        skip
    udahan
udahan
```

### Data types

There are the following data types currently supported:

1. Numeric

```
number1 = 1
number2 = 2.
number3 = 3.21
number4 = 0.432
number5 = .543
number6 = -1
```

2. Text

```
text = "hello world"
```

3. Logical

```
logical1 = true
logical2 = false
```

4. Class

```
class <class name> [ <property1>, <property2>, ...  ]
    # inner statements
    spill <property1>

    so <function name> [ <property1>, <property2> ]
        # function statements
        this :: <property1> = <property1>
    udahan
udahan

class Lamp [ type, is_on ]
    so turn_on []
        is_on = true
    udahan

    so turn_off []
        is_on = false
    udahan

    so set_is_on [ is_on ]
        this :: is_on = is_on
    udahan
udahan

lamp_instance = new Lamp [ "Halogen", false ]

# get/set class's property
lamp_is_on = lamp_instance :: is_on
lamp_instance :: type = "Led"

# invoke class's function
lamp_instance :: turn_off []
```

5. Arrays

```
<array> = { <value1>, <value2>, ... }
example_array = { 1, 2, "three", new TreeNode [ 4 ] }
empty_array = {}

# get an array's value
<value> = <array> { <index> }
value = items{1}

# set an array's value
<array> { <index> } = <value>
items{1} = 123

# append a value to array
<array> << <value>
items = {1,2}
items << 3  #{1,2,3}
```

6. Null

```
value = null
```