# MIPS32 Simulator
This repository contains the source code for a MIPS32 code Assembler written in Java. The application is capable of assembling and simulating MIPS32 assembly code.

## Table of Contents
- [Prerequisites](#Prerequisites)
- [Getting Started](#Getting-Started)
- [Usage](#Usage)
- [File Descriptions](#File-Descriptions)
- [Test Assembly Code](#Test-Assembly-Code)
- [MIPS Simulator Simulation Process](#MIPS-Simulator-Simulation-Process)
## Prerequisites
To run the project, ensure that you have the following installed on your system:

Java Development Kit (JDK) version 8 or above
JavaFX library (included in JDK until Java 10)
## Getting Started
To get started with Jubiter, follow these steps:

Clone the repository:

bash
Copy code
```bash 
git clone https://github.com/abdelrhmankadry/Jubiter-assembler.git
```
Open the project in your preferred Java IDE.

Build the project using the IDE's build functionality.

Run the Main class located in the src directory to launch the simulator application.


## Usage
The Jubiter assembler provides a graphical user interface (GUI) for assembling and simulating MIPS32 assembly code. The GUI allows you to perform the following actions:

Select the source file: Use the "Source File" button to browse and select the MIPS32 assembly code file (*.txt) you want to assemble and simulate.

Assemble and simulate: Click the "Run" button to assemble and simulate the selected source file. The output will be displayed in the text area.

View register file: Click the "RegFile" button to open a new window displaying the contents of the MIPS32 register file.

View memory: Click the "Memory" button to open a new window displaying the contents of the MIPS32 memory.




Please note that the simulator assumes a specific format for the MIPS32 assembly code. Refer to the MIPS32 Assembly Code Guidelines for details on the supported instructions and syntax.

## File Descriptions
The repository contains the following files:

RegFile.java: Defines the RegFile class, which represents the register file in the MIPS32 Simulator.

Repository.java: Defines the Repository class, which provides utility methods for managing the statement counter and labels in the MIPS32 Simulator.

Statement.java: Defines the Statement class, which represents a single statement in the MIPS32 assembly code and provides methods for parsing and extracting its components.

Controller.java: Defines the Controller class, which implements the logic for the GUI of the MIPS32 Simulator.

Main.java: Defines the Main class, which serves as the entry point for the MIPS32 Simulator application.

RegFileController.java: Defines the RegFileController class, which controls the GUI for displaying the register file.

Please refer to the source code files for detailed comments and documentation about each class and its functionality.

## Test Assembly Code
The "test assembly code" folder contains sample MIPS32 assembly code files (.txt) that can be used to test the assembler. You can find various examples of assembly code files in this folder. Feel free to explore and use them to simulate different programs.
## MIPS Simulator Simulation Process

This document provides an overview of the simulation process for the MIPS simulator using the iverilog software.

### Simulation Steps

1. **Compilation**:
    - The simulation process begins by compiling a Verilog file that represents the MIPS processor using the iverilog software.
    - The Verilog file contains the hardware description of the MIPS processor, including its components and their interconnections.
    - The compilation process converts the Verilog code into a binary executable file.

2. **Execution**:
    - Once the Verilog file is compiled, the compiled binary file is executed using the `vvp` command.
    - The `vvp` command launches the simulation and starts executing the instructions defined in the Verilog file.
    - During execution, the MIPS processor emulates the behavior of a real MIPS processor, executing instructions and updating the internal state accordingly.

3. **Output Capture**:
    - As the MIPS simulator executes the instructions, it generates output, such as register values, memory contents, or any other desired information.
    - The output is captured by the simulator and can be accessed for further analysis or verification.
    - In the context of the provided code, the output is captured and stored in the `output` array.

4. **Comparison with Expected Results**:
    - To verify the correctness of the simulation, the simulator compares the captured output with expected results.
    - In the provided code, the expected results are read from a separate file named `solution` that contains the desired output for comparison.
    - The simulator iterates through the captured output and the expected results, checking for any discrepancies.
    - If a mismatch is found between the output and the expected results, an error message is generated.

### Conclusion

The simulation process for the MIPS simulator involves compiling a Verilog file, executing it using the `vvp` command, and capturing the output for comparison with expected results. This process allows for testing and verification of the MIPS processor's behavior and correctness.

Note: The specifics of the simulation process may vary depending on the implementation  MIPS simulator.


## Screenshots

### Main screen
![main screen](https://github.com/abdelrhmankadry/Jupiter-assembler/blob/main/screenshots/main%20screen.png?raw=true)
### Example assembly code
![input code](https://github.com/abdelrhmankadry/Jupiter-assembler/blob/main/screenshots/input.png?raw=true)
### Output 
![output zeros and ones](https://github.com/abdelrhmankadry/Jupiter-assembler/blob/main/screenshots/output.png?raw=true)

