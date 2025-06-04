package main.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionBank {
    private List<Question> easyQuestions;
    private List<Question> mediumQuestions;
    private List<Question> hardQuestions;
    private Random random;

    public QuestionBank() {
        easyQuestions = new ArrayList<>();
        mediumQuestions = new ArrayList<>();
        hardQuestions = new ArrayList<>();
        random = new Random();
        populateQuestions();
    }

    private void populateQuestions() {
        // Easy Questions
        easyQuestions.add(new Question("I’m not a dog, but I guard your digital home. What am I?", "FIREWALL", "easy"));
        easyQuestions.add(new Question("I rhyme with 'go' and help the web flow. What am I?", "PROTOCOL", "easy"));
        easyQuestions.add(new Question("I’m a window to the world, not made of glass. What am I?", "BROWSER", "easy"));
        easyQuestions.add(new Question("I keep your files in tidy rows, with tables and columns where your data goes. What am I?", "DATABASE", "easy"));
        easyQuestions.add(new Question("Click me to open or to close, I’m a symbol everyone knows.", "ICON", "easy"));
        easyQuestions.add(new Question("I help you type your thoughts all day, with letters and symbols laid out each way.", "KEYBOARD", "easy"));
        easyQuestions.add(new Question("I’m a picture on screen, I move with a click, guiding your way to the app that you pick.", "CURSOR", "easy"));
        easyQuestions.add(new Question("I carry power, that’s my role, from wall to laptop, that’s my goal.", "CHARGER", "easy"));
        easyQuestions.add(new Question("You can’t see me, but I help you connect. Phones and laptops, I help direct.", "WIFI", "easy"));
        easyQuestions.add(new Question("I’m where deleted files go to sleep, until you choose to take a leap.", "RECYCLE BIN", "easy"));
        easyQuestions.add(new Question("I’m the place where you see your start, with apps and files to pick apart.", "DESKTOP", "easy"));
        easyQuestions.add(new Question("I’m the letter you save work under, usually ‘C’, I’m full of thunder.", "DRIVE", "easy"));
        easyQuestions.add(new Question("I open files when you give a click, I start up programs really quick.", "APPLICATION", "easy"));
        easyQuestions.add(new Question("I turn data into pretty scenes, with charts and graphs on vivid screens.", "VISUALIZATION", "easy"));
        easyQuestions.add(new Question("I’m used for slides to present with grace, helping ideas take their place.", "PRESENTATION", "easy"));
        easyQuestions.add(new Question("I’m a file that tells the printer, how your document should enter.", "PDF", "easy"));
        easyQuestions.add(new Question("You log in with me to be known, without my match, access is blown.", "PASSWORD", "easy"));
        easyQuestions.add(new Question("I’m made of keys but don’t unlock doors, I help you code and do much more.", "KEYBOARD", "easy"));
        easyQuestions.add(new Question("I’m a line that leads to a place on the net, you type me in when your browser is set.", "URL", "easy"));
        easyQuestions.add(new Question("I shrink files small to send with glee, just double-click to set them free.", "ZIP FILE", "easy"));

        // Medium Questions
        mediumQuestions.add(new Question("Apps on Android know me well. I start with “J” and cast my spell.", "JAVA", "medium"));
        mediumQuestions.add(new Question("I describe traits both big and small, I help define entities, one and all. What am I?", "ATTRIBUTES", "medium"));
        mediumQuestions.add(new Question("I help you surf but not the sea, with tabs and links, come click on me.", "BROWSER", "medium"));
        mediumQuestions.add(new Question("I guard your files from bugs and worms, my updates come in waves and terms.", "ANTIVIRUS", "medium"));
        mediumQuestions.add(new Question("I’m like a house with no front door, I keep your files and even more.", "SERVER", "medium"));
        mediumQuestions.add(new Question("I’m the brain, I do the thinking, Without me, your PC’s not blinking.", "CPU", "medium"));
        mediumQuestions.add(new Question("I store things temporarily when you play, Turn off the power, and I go away.", "RAM", "medium"));
        mediumQuestions.add(new Question("I spin or flash to store your files, From photos to apps, I do it in piles.", "HARD DRIVE", "medium"));
        mediumQuestions.add(new Question("I show you images, games, and light, With pixels glowing, I’m a delight.", "MONITOR", "medium"));
        mediumQuestions.add(new Question("I’m built for games, graphics, and more, I make visuals that you adore.", "GPU", "medium"));
        mediumQuestions.add(new Question("I point and click but never speak, I guide your hands when answers you seek.", "MOUSE", "medium"));
        mediumQuestions.add(new Question("I store your BIOS and keep settings intact, Even when off, I remember the pact.", "CMOS BATTERY", "medium"));
        mediumQuestions.add(new Question("I offer ports, a case, and space, I house your parts in one safe place.", "COMPUTER CASE", "medium"));
        mediumQuestions.add(new Question("I let you hear sounds, songs, and beeps, Plug in your headphones, and sound leaps.", "SOUND CARD", "medium"));
        mediumQuestions.add(new Question("I shine red light and see the floor, I move your pointer, more and more.", "OPTICAL MOUSE", "medium"));
        mediumQuestions.add(new Question("I provide a path for data to flow, Inside your case, I’m the hidden show.", "BUS", "medium"));
        mediumQuestions.add(new Question("I’m a form of memory that keeps data still, Even when off, I won’t spill.", "ROM", "medium"));
        mediumQuestions.add(new Question("I can copy paper, black or blue, Scan and print, I do that too.", "ALL-IN-ONE PRINTER", "medium"));
        mediumQuestions.add(new Question("I connect outside gadgets to your core, I’m tiny and shaped like a little door.", "USB PORT", "medium"));
        mediumQuestions.add(new Question("I’m small and portable, a storage dream, Slide me in, and data streams.", "USB FLASH DRIVE", "medium"));

        // Hard Questions
        hardQuestions.add(new Question("I hold your data structured and clean, in rows and columns mostly seen.", "DATABASE", "hard"));
        hardQuestions.add(new Question("I link tables with a key that’s not mine, Bringing relations by design.", "FOREIGN KEY", "hard"));
        hardQuestions.add(new Question("I am the keeper of meaning and name, With me, your data won’t play a guessing game. What am I?", "DATA DICTIONARY", "hard"));
        hardQuestions.add(new Question("I secure your secrets, lock and seal, without my code, they’re easy to steal.", "ENCRYPTION", "hard"));
        hardQuestions.add(new Question("In a system, I have a name, A person or thing that plays the game. With details that help you describe me well, I live in tables where data dwells. What am I?", "ENTITY", "hard"));
        hardQuestions.add(new Question("I’m the language for asking data questions; I’m structured with specific expressions.", "SQL", "hard"));
        hardQuestions.add(new Question("I trace every change from source to display, Tracking each step along the way.", "DATA LINEAGE", "hard"));
        hardQuestions.add(new Question("I control who sees what and when, Guarding data again and again.", "ACCESS CONTROL", "hard"));
        hardQuestions.add(new Question("I’m the rulebook data must obey, Ensuring compliance every day.", "DATA GOVERNANCE", "hard"));
        hardQuestions.add(new Question("I store data unstructured and wide, Scaling fast on the big data ride.", "NOSQL DATABASE", "hard"));
        hardQuestions.add(new Question("I log every action, silent and true, Helping audits trace what users do.", "AUDIT TRAIL", "hard"));
        hardQuestions.add(new Question("Sensitive data I shield and disguise, Protecting secrets from prying eyes.", "DATA ENCRYPTION", "hard"));
        hardQuestions.add(new Question("I’m a plan for disaster’s unwelcome day, Backups safe so work can stay.", "BACKUP STRATEGY", "hard"));
        hardQuestions.add(new Question("I automate data’s journey and flow, From source to sink where insights grow.", "DATA PIPELINE", "hard"));
        hardQuestions.add(new Question("I govern the ethics of data use, Balancing power to reduce abuse.", "DATA ETHICS", "hard"));
        hardQuestions.add(new Question("I hold data steady for fast retrieval, Reducing delays with quick approval.", "DATA CACHE", "hard"));
        hardQuestions.add(new Question("I destroy data beyond recall, Ensuring privacy stands tall.", "DATA SANITIZATION", "hard"));
        hardQuestions.add(new Question("I analyze patterns and predict the new, Turning raw data into a clue.", "DATA ANALYTICS", "hard"));
        hardQuestions.add(new Question("I control the flow between systems diverse, Keeping data fresh in a seamless course.", "DATA INTEGRATION", "hard"));
        hardQuestions.add(new Question("I store temporary data for quick recall, Speeding up access, preventing a stall.", "CACHE MEMORY", "hard"));
    }

    public Question getRandomQuestion(String difficulty) {
        List<Question> selectedList;

        switch (difficulty.toLowerCase()) {
            case "easy":
                selectedList = easyQuestions;
                break;
            case "medium":
                selectedList = mediumQuestions;
                break;
            case "hard":
                selectedList = hardQuestions;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        }

        if (selectedList.isEmpty()) {
            return null;
        }

        return selectedList.get(random.nextInt(selectedList.size()));
    }
}
