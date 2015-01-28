package com.example.chris.scaledbuttons;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static final int NUM_BUTTONS = 5; // Number of BUTTONS (Columns in table)
    TableLayout table;

    Button[] buttons = new Button[NUM_BUTTONS]; // Array for buttons

    //ArrayList<IconInfo> drawableList = new ArrayList<>(); // Array list of type IconInfo for drawables

    IconList drawableList = new IconList(this); // Array list of type IconInfo for drawables
    String[] iconUnderNames = {"x_ic_launcher", "x_icon_trail_under", "x_icon_qr_under", "x_icon_map_under", "x_icon_information_under"};
    String[] iconOverNames = {"x_icon_question_over", "x_icon_trail_over", "x_icon_qr_over", "x_icon_map_over", "x_icon_information_over"};

//    Drawable[] overIcon = {R.drawable.icon_trail_under};
//    R rUnder = R.drawable.icon_question_under;
//    new Drawable[NUM_BUTTONS]; // Holds pressed icons
    //Drawable[] underIcon = new Drawable[NUM_BUTTONS]; // Holds default icons


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateButtons();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void populateButtons() {

        table = (TableLayout) findViewById(R.id.bottomTableForButtons); // Table to be populated


        IconList iconUnder = new IconList(this); // Initialise under icon (representing buttons not pressed) list passing this activity
        IconList iconOver = new IconList(this);  // Initailise over icon (representing buttons pressed) list passing this activity
        int iconId = 0;

        // Initialise and set up icon lists
        // Field[] fields = R.drawable.class.getDeclaredFields(); // Get all of the Drawables from R

        // Get drawables from R and populate drawableList
        getDrawables();


        // Resources res = getResources();

        // int resource = R.drawable.icon_question;

        //Drawable drawable = res.getDrawable(resource);


        TableRow tableRow = new TableRow(this); // Create new table row

        table.addView(tableRow);  // Add the row to the table
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, // Width
                180/*TableLayout.LayoutParams.MATCH_PARENT*/, // Height
                1.0f)); // Scale


        for(int col = 0; col < NUM_BUTTONS; col++) {
            final int finalCol = col;
            Button button = new Button(this); // Create button

            //button.setText("B" + col); // Add example text

            button.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, // Width
                    TableRow.LayoutParams.MATCH_PARENT, // Height
                    1.0f)); // Scale

            button.setPadding(0, 0, 0, 0);

//            drawablelist.indexOf(new Tuple<String, Drawable>(iconUnderNames[col]), )



            button.setBackground(getResources().getDrawable(drawableList.getId(iconUnderNames[col])));

//            ///// Add image to button
//            for(Field f : fields) {
//                if (f.getName().equals(iconUnderNames[col])) {
//                    iconId = getResources().getIdentifier(iconUnderNames[col], "drawable", this.getPackageName());
//                    break;
//                }
//            }
//
//            if(iconId == 0) { // Error check
//                iconId = R.drawable.x_icon_qr;
//            }
//
//            Drawable dbl = getResources().getDrawable(iconId);
//
//            button.setBackground(dbl);

            System.out.println("<<<<<<<<<<<<<<< OK Here >>>>>>>>>>>>>>>>>");

            // How do we get the right drawable from the list based on iconUnderNames[col] ?
            // button.setBackground(drawablelist.);

            ////// end add image

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gridButtonClicked(finalCol);
                }
            });
            tableRow.addView(button); // Add button to the table
            buttons[col] = button; // Add the button to the buttons array

        }
    }


    /**
     * Tom's getDrawables
     * Gets the drawable items from the resources based on name starting with x_
     * and popluates drawableList
     *
     */
    private void getDrawables() {

        final Field[] fields = R.drawable.class.getDeclaredFields();

///////////////////////////////////// Chris hack
//        int icon = 0;
//        ImageButton btn = new ImageButton(getBaseContext());
//
//        String iconName="icon_question_over";
//
//        for(Field f : fields) {
//            if (f.getName().equals(iconName)) {
//                icon = getResources().getIdentifier(iconName, "drawable", this.getPackageName());
//                break;
//            }
//        }
//
//        Drawable dbl = getResources().getDrawable(icon);
//
//        btn.setBackground(dbl);
//////////////////////////////////////////

        for (Field F : fields) {
            try
            {
                if (F.getName().startsWith("x_")) {
                    String name = F.getName();
                    int id = getResources().getIdentifier(name, "drawable", this.getPackageName()); // Get the resource ID
                    //Drawable d = getResources().getDrawable(id); // Don't need to get this, just retrieve id instead

                    drawableList.add(new IconInfo(name, id)); // Add IconInfo with icon details
                }

            }
            catch(Exception ex)
            {
                System.out.println("Could not load icon ");

            }
        }

        // Debuging output, itterate throught the icon list and print info
        System.out.println("Icons found:");
        for(IconInfo icn : drawableList.icons) {
            System.out.println(icn.name + " : " + icn.iconID);
        }
    }



    private void gridButtonClicked(int btn) { // Show Toast message
//        Field field;
//        int drawableId;
//        Class resources = R.drawable.class;
////////////////////////////////
//        final Field[] fields = R.drawable.class.getDeclaredFields();
///////////////////////////////////////
//
//
//        int icon = 0;
//        ImageButton btn = new ImageButton(getBaseContext());
//
//        String iconName="icon_question_over";
//
//        for(Field f : fields) {
//            if (f.getName().equals(iconName)) {
//                icon = getResources().getIdentifier(iconName, "drawable", this.getPackageName());
//                break;
//            }
//        }
//
//        Drawable dbl = getResources().getDrawable(icon);
//
//        btn.setBackground(dbl);
//
////////////////////////////////////////
//        R.drawable test;
//
//        Resources res = getResources();
//
//        //int resource = R.drawable.icon_question;
//
//        int newWidth = buttons[btn].getWidth();
//        int newHeight = buttons[btn].getHeight();
//
//        Bitmap originalBitmap = BitmapFactory.decodeResource(res, R.drawable.icon_question_under);
//        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
//
//
//        Drawable drawable = res.getDrawable(resource);


//        try {
//
//            field = resources.getField("icon_question_under.png");
//            drawableId = field.getInt(null);
//        }
//        catch (Exception e) {
//            Log.e("MyTag", "Failure to get drawable id.", e);
//            return;
//        }
//
//        System.out.println("drawableId =" + drawableId);

        // Scale image to button
//        newWidth = buttons[btn].getWidth();
//        newHeight = buttons[btn].getHeight();



        //Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_question_under);
//        Bitmap originalBitmap = BitmapFactory.decodeResource(res, drawableId);
//        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);

        //Resources resource = getResources();
//        buttons[btn].setBackground(new BitmapDrawable(res, scaledBitmap));

        Toast.makeText(this, "Button Clicked " + btn, Toast.LENGTH_SHORT).show();
        resetButtonBackground(btn);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void resetButtonBackground(int btn)
    {
       // int index=0;

        for(int i = 0; i < 5; i++)
        {
            if(i != btn)
            {
                int id = drawableList.getId(iconUnderNames[i]);
                buttons[i].setBackground(getResources().getDrawable(id));
               // index++;
            }
            else
            {
                int id = drawableList.getId(iconOverNames[i]);
                buttons[i].setBackground(getResources().getDrawable(id));
                //index++;
            }
        }
        table.requestLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
