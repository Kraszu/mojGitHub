

    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    #include "game.h"

    //Maciej Kubiniec R00144142
    int counter = 0;
    void play_game(){

        struct game *p_game_info = 0;
        //Use malloc to allocate memory
        p_game_info = malloc(sizeof(struct game));
        initialise_game (p_game_info, "John", "Annie");
        draw_banner();
        display_board(p_game_info->board);
        system("cls");
        display_board_positions ();
        draw_banner();
        // p_game_info->board[0][0] = X_SYMBOL; // top left X
        // p_game_info->board[2][2] = O_SYMBOL; // bottom right o
        while(p_game_info->finished==False){
            system("cls");
            display_board_positions ();
            draw_banner();
            display_board(p_game_info->board);
            print_status ( p_game_info );
            process_move( p_game_info);
            finished_game(p_game_info);

        }

    }

    //Initialise the fields in the  game struct
    void initialise_game(struct game* p_game_info, char* name1, char* name2){
        //set  all the values of the board to SPACE
        for (int r=0; r<3; r++)
        for(int c=0; c<3; c++)
        p_game_info->board[r][c] = SPACE;

        p_game_info->status=P1_TURN;
        p_game_info->finished = False;
        strncpy(p_game_info->playerNames[0], name1,MAX_NAME_LEN);
        strncpy(p_game_info->playerNames[1], name2,MAX_NAME_LEN);

    }

    //method   to draw a banner
    void draw_banner(){

        printf("---------------\n");
        printf("  GAME STATUS  \n");
        printf("---------------\n");
    }

    //draw a picture of the board.
    void display_board(  char board[3][3]){
        printf("---------------\n");
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                    {
                        printf(" %c ", board[i][j]);
                            if (j != 2)
                            printf("|");
                            }
                            if (i != 2)
                            printf("\n-----------");
                            printf("\n");
                            }
                            printf("---------------\n");
    }

    //print the status of the game info
    void print_status (struct game*p_game_info ){
        finished_game( p_game_info);
        if ((p_game_info->finished==False)&&(!p_game_info->status==DRAW)&&
            (p_game_info->status==P1_TURN))
            {
                printf("John's Turn\n");
            }
        else if ((p_game_info->finished==False)&&(!p_game_info->status==DRAW)&&
        (p_game_info->status==P2_TURN))
            {
                printf("Annie's Turn\n");
            };
        if((p_game_info->finished==True)&&
        (p_game_info->status==P1_WON))
            {
                printf( "Well done John, you have won!!!\n");
            }
        else if((p_game_info->finished==True)&&
        (p_game_info->status==P2_WON))
            {
                printf("Well done Annie, you have won!!!\n");
            }
        else if((p_game_info->finished==True)&&
        (p_game_info->status==DRAW))
            {
                printf("Game Over!!! It is a draw!\n");
            }


    }

    //Display the id of the slots on the board
    void display_board_positions (){

        int count = 0;

        printf("---------------\n");

        for (int i = 0; i < 3; i++){
        for (int j = 0; j < 3; j++){
        printf(" %d ", count);
        count++;
            if (j != 2)
            printf("|");
            }
            if (i != 2)
            printf("\n-----------");

            printf("\n");
            }
            printf("---------------\n");

    }

    //calculates the row and col  associated with this position
    void get_row_col(int position, int* row, int* col){

        int count = 0;

        for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            if(count == position){
            *row = i;
            *col = j;
            }
                count++;
            }
        }
    }

   //Process a single movement
    void  process_move(struct game* p_game_info){

        if(p_game_info->status==P1_TURN){
            printf("Choose slot from 0 to 8 to place'X'\n");
            int row = -1;
            int col = -1;
            int Pos;
            scanf("%d", &Pos);
            get_row_col(Pos, &row, &col);
        if(p_game_info->board[row][col]==SPACE){
            p_game_info->board[row][col]=X_SYMBOL;
            p_game_info->status=P2_TURN;
            system("cls");
        }}


        else if (p_game_info->status==P2_TURN){
            printf("Choose slot from 0 to 8 to place'O'\n");
            int row = -1;
            int col = -1;
            int Pos;
            scanf("%d", &Pos);
            get_row_col(Pos, &row, &col);
        if(p_game_info->board[row][col]==SPACE){
            p_game_info->board[row][col]=O_SYMBOL;
            p_game_info->status=P1_TURN;
            system("cls");
        }}


        counter++;
        display_board_positions ();
        draw_banner();
        display_board(p_game_info->board);
        print_status ( p_game_info );


    }

    //Determinate winner or call a draw
    void finished_game(struct game* p_game_info){

        if((p_game_info->board[0][0]==O_SYMBOL)&&(p_game_info->board[0][1]==O_SYMBOL)&&(p_game_info->board[0][2]==O_SYMBOL)||

        (p_game_info->board[1][0]==O_SYMBOL)&&(p_game_info->board[1][1]==O_SYMBOL)&&(p_game_info->board[1][2]==O_SYMBOL)||

        (p_game_info->board[2][0]==O_SYMBOL)&&(p_game_info->board[2][1]==O_SYMBOL)&&(p_game_info->board[2][2]==O_SYMBOL)||

        (p_game_info->board[0][0]==O_SYMBOL)&&(p_game_info->board[1][0]==O_SYMBOL)&&(p_game_info->board[2][0]==O_SYMBOL)||

        (p_game_info->board[0][1]==O_SYMBOL)&&(p_game_info->board[1][1]==O_SYMBOL)&&(p_game_info->board[2][1]==O_SYMBOL)||

        (p_game_info->board[0][2]==O_SYMBOL)&&(p_game_info->board[1][2]==O_SYMBOL)&&(p_game_info->board[2][2]==O_SYMBOL)||

        (p_game_info->board[0][0]==O_SYMBOL)&&(p_game_info->board[1][1]==O_SYMBOL)&&(p_game_info->board[2][2]==O_SYMBOL)||

        (p_game_info->board[0][2]==O_SYMBOL)&&(p_game_info->board[1][1]==O_SYMBOL)&&(p_game_info->board[2][0]==O_SYMBOL))
        {
            p_game_info->status=P2_WON;
            p_game_info->finished=True;

            return;
        }

        else if(((p_game_info->board[0][0]==X_SYMBOL)&&(p_game_info->board[0][1]==X_SYMBOL)&&(p_game_info->board[0][2]==X_SYMBOL))||

        ((p_game_info->board[1][0]==X_SYMBOL)&&(p_game_info->board[1][1]==X_SYMBOL)&&(p_game_info->board[1][2]==X_SYMBOL))||

        ((p_game_info->board[2][0]==X_SYMBOL)&&(p_game_info->board[2][1]==X_SYMBOL)&&(p_game_info->board[2][2]==X_SYMBOL))||

        ((p_game_info->board[0][0]==X_SYMBOL)&&(p_game_info->board[1][0]==X_SYMBOL)&&(p_game_info->board[2][0]==X_SYMBOL))||

        ((p_game_info->board[0][1]==X_SYMBOL)&&(p_game_info->board[1][1]==X_SYMBOL)&&(p_game_info->board[2][1]==X_SYMBOL))||

        ((p_game_info->board[0][2]==X_SYMBOL)&&(p_game_info->board[1][2]==X_SYMBOL)&&(p_game_info->board[2][2]==X_SYMBOL))||

        ((p_game_info->board[0][0]==X_SYMBOL)&&(p_game_info->board[1][1]==X_SYMBOL)&&(p_game_info->board[2][2]==X_SYMBOL))||

        ((p_game_info->board[0][2]==X_SYMBOL)&&(p_game_info->board[1][1]==X_SYMBOL)&&(p_game_info->board[2][0]==X_SYMBOL)))
        {
            p_game_info->status=P1_WON;
            p_game_info->finished=True;

            return;
        }
        else if (counter>=9){
            p_game_info->status=DRAW;
            p_game_info->finished=True;
            return;}


}
