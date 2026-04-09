---
trigger: manual
description: 
globs: 
---

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
## Notes:

- The virtual environment is located at `/Users/vdsimako/dev/education/learnPython/venv`
- Dependencies are managed through `requirements.txt`
- Each day folder can use the same virtual environment
- Always activate the venv before running Python scripts or tests