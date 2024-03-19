VIDEO:  https://youtu.be/kmnGz6f4ooI

GITHUB: https://github.com/Emily-Kwok/A3-COMP3005A.git

JAVA APPLICATION SETUP:

   1. Download the repository from GitHub.
   2. Open the folder (the repository) in a Java IDE.
        > I'm using IntelliJ to run my application.
   3. Run program.

ISSUES: 

   NONE

NOTES: 

   The resolution of the video is low due to my video editor.
   This may be hard to read input and output on the console.
   For clarification, please consult this playlist containing
   raw demo video clips: 
      https://www.youtube.com/playlist?list=PL_e9sp6PoIkQyJkLzuei64AjKV4GdTTIb

   Whenever the program asks the user for an integer input, 
   it will check if it is an integer and if it is within the
   range such as the option in the menu (eg. 9 is not valid).

   If the inputted ID does not exist when updating a 
   student's email or deleting a student, it will still
   execute the query, but the table will not change
   since student by ID does not exist.

   When deleting a student, whether in the middle or at the 
   end, and adding a student right after, it will increment 
   that last known integer that was incremented to. For 
   example, if I were to add a student to the default table, 
   the student ID is 4, but when deleting student 4 and 
   adding another student, it will increment to 5 instead 
   of 4.