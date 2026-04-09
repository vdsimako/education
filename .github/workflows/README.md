# GitHub Workflows Template Guide

## Simplified Workflow Structure

### 1. Reusable Template (`reusable-test-day.yml`)
- Contains all common setup steps
- Accepts `day_name` as parameter
- Handles both test and non-test directories

### 2. Individual Day Workflows
- Only 15 lines vs 41 lines previously
- Specify path triggers and day name
- Use `uses:` to call reusable template

### 3. Matrix-Based Workflow (`test-learnpython.yml`)
- Automatically detects changed days
- Runs tests in parallel for multiple days
- Most efficient for large projects

## Creating New Day Workflows

For future days, create a new workflow file with this template:

```yaml
name: Run Tests for day_X

on:
  pull_request:
    branches: [ master ]
    types: [ opened, synchronize, reopened, ready_for_review ]
    paths:
      - 'learnPython/day_X/**'

jobs:
  test:
    uses: ./.github/workflows/reusable-test-day.yml
    with:
      day_name: day_X
```

## Benefits

1. **DRY Principle**: No code duplication
2. **Maintenance**: Single place to update test logic
3. **Consistency**: All tests run identically
4. **Flexibility**: Easy to add new days
5. **Efficiency**: Matrix approach for parallel execution

## Migration Strategy

1. ✅ Created reusable template
2. ✅ Simplified existing workflows  
3. 🔄 Consider migrating to matrix approach for better efficiency
4. 📝 Update this guide when adding new features
