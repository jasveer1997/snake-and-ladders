# snake-and-ladders

Important points: 

1. Some config like dice, numbers of snake, ladders, board size, movement strategy are from config.json. Post running the program, snake, ladder, player details has to be provided as input from cli. It is important to note any ext library (ex: jackson) to parse json/yml is not used, hence implementation to parse json is basic. 
2. It is assumed that if a player is positioned at position other than 1, the player won't ascend/descend through ladders, snake at the start. However, if we want to change this behavior, it can be done easily by rewriting position in board map with pre-calculation from getNextSetOfMoves()
3. Add on of 3 consecutive roll resulting in all 6 resets the accumulated value.
4. GameHandler is the main entry point - which initialize & them triggers simulator, which is the Game driver.
5. For sample simulation, refer sample.log for reference.
