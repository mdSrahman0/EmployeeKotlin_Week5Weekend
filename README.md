# EmployeeKotlin_Week5Weekend

1. Create an object Employee. The class should have the following member variables:
        1. FirstName
        2. LastName
        3. StreetAddress
        4. City
        5. State
        6. Zip
        7. TaxID
        8. Position
        9. Department
2. Create a database (Room or Sqlite) for the Employee object.
1. MainActivity
    1. Make this into a splash screen activity.
    2. After any initializations for the applications, start the FilterEmployeeActivity.
2. FilterEmployeeActivity
    1. Be creative, but must at least use one spinner to select department of the employee. DEPARTMENTS MUST BE RETRIEVED FROM DATABASE.
    2. This activity will start the EmployeeListActivity.
3. EmployeeListActivity
    1. List all the employees matching the criteria selected in the FilterEmployeeActivity.
    2. Implement a Navigation drawer here for the following activities. Since this activity will also be called from the
    Employee details fragment, you will need to devise a way to get the info for a specific employee both without the employee
    known and with the employee known.
4. NewEmployeeFragment
    1. Allows the user to enter a new employee into the database.
    2. Once operation is complete, go back to the Listing Activity.
    3. When insert is complete, display a toast letting user know which employee was inserted
5. DeleteEmployeeFragment
    1. Allow user to delete an employee from the database.
    2. Once operation is complete, go back to the Listing activity.
    3. When delete is complete, display a toast letting user know which employee was deleted
6. UpdateEmployeeFragment
    1. Allow user to update an employee from the database.
    2. Once operation is complete, go back to the Listing activity.
    3. When update is complete, display a toast letting user know which employee was updated
7. EmployeeDetailsFragment
    1. Display all info about the employee.
    2. Be able to pass the employee to the following activities:
                1. DeleteEmployeeFragment
                2. UpdateEmployeeFragment
4. ALL DATABASE OPERATIONS MUST BE HANDLED BY A WORKER THREAD. You can use any scheme we have covered or one of the following:
    1. Loopers
    2. Loaders
    3. ThreadPools
5. All activities/fragments except Main, filter, and list activities must have backward support. ]
(The back arrow in the left side of action bar)
6. Any activity that requires user input must account for configurational changes in some way.
7. Feel free to experiment with any UI element

NOTE - UPGRADE FRAGMENT STILL DOESN'T WORK. DATABASE QUERY ISSUE

![s1](https://user-images.githubusercontent.com/51377429/61175932-ddaf5700-a585-11e9-9a2c-02dea44108a7.jpg)
![s2](https://user-images.githubusercontent.com/51377429/61175933-e0aa4780-a585-11e9-8fe5-4906c13d0b19.jpg)
![s3](https://user-images.githubusercontent.com/51377429/61175935-e3a53800-a585-11e9-8e52-4e37665aacd7.jpg)
![s4](https://user-images.githubusercontent.com/51377429/61175938-e99b1900-a585-11e9-8d4b-2ce749329676.jpg)
![s5](https://user-images.githubusercontent.com/51377429/61175939-ebfd7300-a585-11e9-913a-1d8bc586a3ff.jpg)
![s6](https://user-images.githubusercontent.com/51377429/61175941-ef90fa00-a585-11e9-977f-1dadc9db4a92.jpg)
![s7](https://user-images.githubusercontent.com/51377429/61175943-f3248100-a585-11e9-816e-161d737804ab.jpg)
![s8](https://user-images.githubusercontent.com/51377429/61175944-f6b80800-a585-11e9-880d-000f0b73461f.jpg)
![s9](https://user-images.githubusercontent.com/51377429/61175945-f91a6200-a585-11e9-92b8-a483b1dedd62.jpg)
![s10](https://user-images.githubusercontent.com/51377429/61175947-fc155280-a585-11e9-81b8-de5414caa11d.jpg)
