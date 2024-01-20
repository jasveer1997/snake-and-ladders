# snake-and-ladders

Important points: 

1. Some config like dice, numbers of snake, ladders, board size, movement strategy are from config.json. Post running the program, snake, ladder, player details has to be provided as input from cli. It is important to note any ext library (ex: jackson) to parse json/yml is not used, hence implementation to parse json is basic. 
2. It is assumed that if a player is positioned at position other than 1, the player won't ascend/descend through ladders, snake at the start. However, if we want to change this behavior, it can be done easily by rewriting position in board map with pre-calculation from getNextSetOfMoves()
3. Add on of 3 consecutive roll resulting in all 6 resets the accumulated value.
4. GameHandler is the main entry point - which initialize & them triggers simulator, which is the Game driver.
5. For sample simulation, refer sample.log for reference.
6. Basic test cases are written in test/TestSL.java - Again this is implemented without any external library.
7. Added a separate implementation of Crocodile for now, however, as per rules, crocodiles are better yet a specific type of snake only. (Similar to snake ~ ladder with opposite behavior)

Validations include: (All classified as bad input exception)

    off board detection ([1, board_size*board_size])
    cycles by board elements
    Strict ladder behavior (rows)
    Reverse snakes/ladders

