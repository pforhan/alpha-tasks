# Developer notes

* Minimize manifest permissions -- make use of intents
* Set up parallel databases in getFilesDir() and getNoBackupFilesDir() to move tasks between the two quickly
    * Events should be collated and displayed side by side in memory
* Use Jetpack compose for the UI and dealing with changing state.
* UI should be sparse and simple
* todayTasks is defined as incomplete tasks with a "show date" of: unspecified (null), today, or in the past.
* rough UI sketch:
    * App title is Alpha's Tasks
    * There's a settings button near the app title that opens a panel to provide some options.  Some options include:
        * default setting for allow play store backup
        * move all tasks between backup and nobackup
    * There's a "+" button or equivalent near the settings button to add a task
    * Add/Edit page should have, in this order:
        * Task -- Text field for the task title
        * Details -- text area with multiline content
        * start date (optional)
        * repeat info (optional, defaults to not repeating)
          * numeric textfield for count
          * selector for days, weeks, months, years
    * Main UI is a list showing todayTasks.
        * Swiping an event to the right will postpone its show date to tomorrow, dropping it from the list
    * Tapping an task will show details for that task
        * Available actions on detail pane: complete, edit, delete, share, postpone
        * otherwise similar to add/edit ui
    * There's a toggle to show tasks for all days
        * This will present them all in "show date" order.
        * When showing all tasks, a swipe-right should enter the edit event screen
        * The toggle changes to a "Show today" while showing all tasks.
    * There's an additional toggle for show/hide completed tasks
      * Should be careful to maintain scroll position by currently viewed show date
    * Long pressing or swiping an event to the left will reveal some quick actions for that event, like edit, delete, and share.
    * The app should show notifications for each task in todayTasks.
        * Summary line should be the task line
        * if expanded, include the details if available
        * Extra actions for each notification: complete, postpone
        * selecting the notification should show the task's detail view
        * dismissing the notification should do the "postpone" action (so it will show tomorrow)
