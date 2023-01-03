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
nek <condition>
    # Block Statement
nekngene <condition>
    # Block Statement
nekora
    # Block Statement
uwes

nek a > 5 and tree_node :: value == 3
    # Block Statement
nekngene b == "hello" or c == "world"
    # Block Statement
nekora
    # Block Statement
uwes
```

3. Print to console

```
ndelok <expression>
ndelok a + b + tree_node :: value
```

4. Input from console

```
nginput <variable name>
nginput number
```

5. Functions

```
guna <function name> [ <argument1, argument2>, ... ]
    <body>
    return <expression>
uwes

guna fibonacci_number [ n ]
    nek n < 2
        return n
    uwes
    return fibonacci_number [ n - 1 ] + fibonacci_number [ n - 2 ]
uwes
```

6. Loops

```
# For loop
ngulang <variable> ing <lower_bound>..<upper_bound>
    # statements
uwes

# Specify the step
ngulang <variable> ing <lower_bound>..<upper_bound> karo <step>
    # statements
    # seed increment statement
uwes

# While loop
ngulang <condition>
    # statements
uwes

# Iterable loop (for-each)
ngulang <variable> ing <iterable>
    # statements
uwes

# terminate the loop
ngulang <variable> ing <lower_bound>..<upper_bound> karo <step>
    nek <other_condition>
        stop
    uwes
uwes

# jump to the next iteration
ngulang <variable> ing <lower_bound>..<upper_bound> karo <step>
    nek <other_condition>
        skip
    uwes
uwes
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
    ndelok <property1>

    guna <function name> [ <property1>, <property2> ]
        # function statements
        this :: <property1> = <property1>
    uwes
uwes

class Lamp [ type, is_on ]
    guna turn_on []
        is_on = true
    uwes

    guna turn_off []
        is_on = false
    uwes

    guna set_is_on [ is_on ]
        this :: is_on = is_on
    uwes
uwes

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