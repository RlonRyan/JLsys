Pythagoras Tree:
    Alphabet: AB
    Production Rules:
        (A → B[+A]-A)
        (B → BB)
    Axiom (Seed): A
    Angle: 45

Koch Curve:
    Alphabet: A
    Production Rules:
        (A → A+A-A-A+A)
    Axiom (Seed): A
    Angle: 90

Koch Curve (Alt.,  From Algo. Beauty of Plants):
    Alphabet: A
    Production Rules:
        (A → AA-A-A-A-A-A+A)
    Axiom (Seed): A-A-A-A
    Angle: 90

Koch Square (From Algo. Beauty of Plants):
    Alphabet: A
    Production Rules:
        (A → AA-A-A-A-AA)
    Axiom (Seed): A-A-A-A
    Angle: 90

Sierpinski Triangle:
    Alphabet: AB
    Production Rules:
        (A → A−B+A+B−A)
        (B → BB)
    Axiom (Seed): A-B-B
    Angle: 120

Sierpinski Triangle using Sierpinski Arrowheads:
    Alphabet: AB
    Production Rules:
        (A → +B-A-B+)
        (B → -A+B+A-)
    Axiom (Seed): A
    Angle: 60

Hexagonal Gosper Curve (From Algo. Beauty of Plants):
    Alphabet: AB
    Production Rules:
        (A → A+B++B-A--AA-B+)
        (B → -A+BB++B+A--A-B)
    Axiom (Seed): A
    Angle: 60

Quadratic Koch Island (From Algo. Beauty of Plants):
    Alphabet: A
    Production Rules:
        (A → A-A+A+AA-A-A+A)
    Axiom (Seed): A-A-A-A
    Angle: 90

Triangles (From L-System Bot 2.0):
    Alphabet: ABCD
    Production Rules:
        (A → -[AB]-)
        (B → +BA)
        (C → -AD+)
        (D → [A]AC[C])
    Axiom (Seed): CC
    Angle: 60

Circuitry (From L-System Bot 2.0):
    Alphabet: ABCDEF
    Production Rules:
        (A → F+DB[FA]-F)
        (B → AC-BA+A)
        (C → +BAE)
        (D → [AA])
        (E → AD)
        (F → F)
    Axiom (Seed): FAFB
    Angle: 60

Symbol Guide:
    - '+' means to turn right by the specified angle.
    - '-' means to turn left by the specified angle.
    - '<' means to increment the color by +10.
    - '>' means to increment the color by -10.
    - '[' means to push the position, angle, and color onto the stack.
    - ']' means to pop the position, angle, and color from the stack.
    - Any symbol that is not a constant or an ignore token is interpreted as a move forward command.

