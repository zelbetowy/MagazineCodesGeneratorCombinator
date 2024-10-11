import openpyxl
from flask import Flask, jsonify

app = Flask(__name__)

EXCEL_PATH = 'D:\\SOFT\\JAVA\\PartsCodesCombinatorGenerator\src\\resources\\PythonScripts\\excelReaderService\\CodeOptions.xlsx'

def read_excel(sheet_name, column_range):
    workbook = openpyxl.load_workbook(EXCEL_PATH)
    sheet = workbook[sheet_name]

    data = []
    for row in column_range:
        row_data = []
        for col in row:
            cell_value = sheet[col].value

            #Deleting nulls from pool
            if cell_value is not None:
                row_data.append(cell_value)

        # Dodajemy tylko niepuste wiersze
        if row_data:
            data.append(row_data)
    return data

# Metoda dla Bolts
@app.route('/read_bolts', methods=['GET'])
def read_bolts():

    ColumnRange1 = 70
    ColumnaRange2 = 101

    range1 = [f'B{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range2 = [f'D{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range3 = [f'F{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range4 = [f'H{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range5 = [f'J{i}' for i in range(ColumnRange1, ColumnaRange2)]


    column_ranges = {
        "range1": range1,
        "range2": range2,
        "range3": range3,
        "range4": range4,
        "range5": range5
    }

    result = {}
    for key, column_range in column_ranges.items():
        # Każdy zestaw danych będzie w osobnym polu
        result[key] = read_excel('Bolts', [column_range])

    # Json Return from method
    return jsonify(result)

@app.route('/read_nuts', methods=['GET'])
def read_nuts():
    ColumnRange1 = 70
    ColumnaRange2 = 101

    range1 = [f'B{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range2 = [f'D{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range3 = [f'F{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range4 = [f'H{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range5 = [f'J{i}' for i in range(ColumnRange1, ColumnaRange2)]
    column_ranges = {
        "range1": range1,
        "range2": range2,
        "range3": range3,
        "range4": range4,
        "range5": range5
    }

    result = {}
    for key, column_range in column_ranges.items():
        # Każdy zestaw danych będzie w osobnym polu
        result[key] = read_excel('Nuts', [column_range])

    # Json Return from method
    return jsonify(result)

@app.route('/read_washers', methods=['GET'])
def read_washers():

    ColumnRange1 = 70
    ColumnaRange2 = 101

    range1 = [f'B{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range2 = [f'D{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range3 = [f'F{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range4 = [f'H{i}' for i in range(ColumnRange1, ColumnaRange2)]
    range5 = [f'J{i}' for i in range(ColumnRange1, ColumnaRange2)]


    column_ranges = {
        "range1": range1,
        "range2": range2,
        "range3": range3,
        "range4": range4,
        "range5": range5
    }

    result = {}
    for key, column_range in column_ranges.items():
        # Każdy zestaw danych będzie w osobnym polu
        result[key] = read_excel('Washers', [column_range])

    # Json Return from method
    return jsonify(result)




if __name__ == '__main__':
    app.run(debug=True, port=5000)