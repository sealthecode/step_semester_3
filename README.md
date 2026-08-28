# step_semester_3

STEP Semester 3 coursework

## Date: 28-08-2026

**Today's Work:**
- Set up the repository branching structure: `main` (README only), `develop` (empty Java project skeleton), and `feature/session_3` created from `develop`
- Created the Week 3 package structure: `oop/class_problems` and `oop/assigment_problems`
- Solved all 5 Category A OOP homework problems:
  - F1 — `BookIssue`: library fine system, instance vs static methods, array of objects
  - F2 — `Employee` / `ManagerEmployee` / `InternEmployee`: inheritance without editing the tested base class, `instanceof` dispatch
  - F3 — `ParkingSlot`: null-safe slot allotment, object references vs copies
  - F4 — `LibraryMember`: reproduced the all-static bug, then redesigned with the correct instance/static split
  - F5 — `CompanyEmployeeRecord`: capstone composition, objects as fields, static counter, null-safe parking
- Verified every program compiles with `javac` and matches the expected sample output

**Next Session Plan:**
- Begin Session 4 on a new `feature/session_4` branch created from `develop`

**Issues Faced:**
- VS Code's Java extension flagged incorrect package declarations because the source root was not configured; fixed by setting `java.project.sourcePaths` in `.vscode/settings.json`
- Accidentally accepted an editor quick-fix that duplicated the class declaration in `BookIssue.java`; restored the file manually

---