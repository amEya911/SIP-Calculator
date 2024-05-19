
# SIP Calculator App

The SIP Calculator app is a versatile tool designed to assist users in calculating potential returns on their investments, whether through Systematic Investment Plans (SIPs) or lump sum investments. This app provides flexibility for users to choose between SIP and Lumpsum calculators, depending on their investment preference. It simplifies the process of estimating returns and visualizes the investment growth through intuitive graphical representations.


## Features

- Dual Calculators: Users can choose between SIP and Lumpsum calculators based on their investment preference.
- Flexible Inputs: Users input four main parameters: investment amount (monthly for SIP, total for Lumpsum), expected rate of return, investment duration in years, and expected inflation rate.
- Detailed Results: The app provides a breakdown of invested amount, returns, total value, and total value after accounting for inflation (purchasing power).
- Visual Representation: A circular progress bar graphically represents the investment growth, making it easy for users to understand.


## How to Use

1.	Open the SIP & Lumpsum Calculator app on your device.
2.	Choose between SIP or Lumpsum calculator.
3.	Enter the investment amount. For SIP, this is the monthly investment amount, while for Lumpsum, it is the total investment amount.
4.	Enter the expected rate of return.
5.	Enter the investment duration in years.
6.	Enter the expected inflation rate.
7.	The app will display the calculated results, including invested amount, returns, total value, and total value after inflation (purchasing power), along with a circular progress bar representing the investment growth.

## Tech Stack

**Jetpack Compose:** Used for building the user interface of the app, providing a modern and declarative way to create UIs.

**Kotlin:** The programming language used for developing the app.

**Android Studio:** The integrated development environment (IDE) used for building and testing the app.


## Acknowledgements and Mentions

-	Jetpack Compose: Our app's user interface is built using Jetpack Compose, a modern Android UI toolkit developed by Google. Compose has made it easier to create dynamic and engaging user interfaces.
-	ViewModel Library: We use the ViewModel library from Android Jetpack to manage UI-related data in a lifecycle-conscious way. It helps in maintaining the data consistency during configuration changes.
-	Splash Screen Library: The core-splashscreen library is used to implement a splash screen in the app. It ensures a smooth and visually appealing start-up experience.

## Technical Flow

1.	User Interaction:
-	The user opens the app and selects either the SIP or Lumpsum calculator.
-	The user inputs the required parameters: investment amount, expected rate of return, investment duration, and inflation rate.
2.	Data Processing:
-	The app validates the input data to ensure all fields are correctly filled.
-	The calculation logic is triggered, which uses the provided inputs to compute the total returns, invested amount, and the final value.
3.	Investment Calculation:
-	For SIP Calculation, the formula used is:

 https://github.com/amEya911/SIP-Calculator/assets/112489532/65ea4a71-ff31-4297-b2d8-65c92e1f9fed

Where:\
•	P = Monthly investment amount \
•	r = Expected rate of return per month (annual rate / 12)\
•	n = Total number of monthly installments (years * 12)

-	For Lumpsum Calculation, the formula used is:

  https://github.com/amEya911/SIP-Calculator/assets/112489532/86d4e684-c3a8-4d20-afc5-83699258326b
 
Where:\
•	P = Total investment amount\
•	r = Expected rate of return per year\
•	n = Investment duration in years

4.	Inflation Adjustment:
-	The app calculates the purchasing power after adjusting for inflation using the formula:

  https://github.com/amEya911/SIP-Calculator/assets/112489532/65ea4a71-ff31-4297-b2d8-65c92e1f9fed
 
Where:\
•	n = Investment duration in years

5.	Display Results:
-	The computed results are displayed to the user, including invested amount, returns, total value, and total value after inflation.
-	A circular progress bar visually represents the investment growth.

6.	User Feedback:
-	The user can modify the input values and recalculate to see updated results.
-	The app provides a seamless and interactive user experience throughout the process.

## Future Enhancements

-	Comparison Tool: Introduce a feature to compare SIP and Lumpsum investment returns side by side.
-	Investment Recommendations: Provide investment recommendations based on user inputs and market trends.
-	Customizable Graphs: Allow users to customize the graphical representation of their investment growth.
-	Investment Alerts: Implement alerts for users to track their investments and receive notifications on significant changes.
-	Additional Investment Options: Include more investment options beyond SIP and Lumpsum for a broader user base.

## Screenshots
https://github.com/amEya911/SIP-Calculator/assets/112489532/14c2619d-a73b-48b1-8d92-d17a32b7f03a
https://github.com/amEya911/SIP-Calculator/assets/112489532/b9776ae8-6c2a-4d63-aff7-f0d4a8db3f05
https://github.com/amEya911/SIP-Calculator/assets/112489532/5cb3411c-b6af-4954-84b9-3b4f103e3505


