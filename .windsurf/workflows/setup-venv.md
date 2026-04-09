---
description: Set up and activate virtual environment for learnPython projects
---

# Python Virtual Environment Setup for learnPython

This workflow helps you set up and use the virtual environment for Python projects in the learnPython folder.

## Steps:

1. **Navigate to learnPython directory**
   ```bash
   cd /Users/vdsimako/dev/education/learnPython
   ```

2. **Create virtual environment (if not exists)**
   ```bash
   python3 -m venv venv
   ```
   // turbo

3. **Activate virtual environment**
   ```bash
   source venv/bin/activate
   ```

4. **Upgrade pip**
   ```bash
   pip install --upgrade pip
   ```

5. **Install dependencies**
   ```bash
   pip install -r requirements.txt
   ```

6. **Verify installation**
   ```bash
   pip list
   ```

## Usage:

- **To activate venv in new terminal sessions:**
  ```bash
  cd /Users/vdsimako/dev/education/learnPython
  source venv/bin/activate
  ```

- **To run tests:**
  ```bash
  cd day_4_1  # or any other day folder
  pytest
  ```

- **To deactivate:**
  ```bash
  deactivate
  ```

## Notes:

- The virtual environment is located at `/Users/vdsimako/dev/education/learnPython/venv`
- Dependencies are managed through `requirements.txt`
- Each day folder can use the same virtual environment
- Always activate the venv before running Python scripts or tests
