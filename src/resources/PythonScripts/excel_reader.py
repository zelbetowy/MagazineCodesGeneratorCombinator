import openpyxl
from flask import Flask, jsonify

app = Flask(__name__)

# Ścieżka do pliku Excel
EXCEL_PATH = 'd:/#SOFT/JAVA/PartsCodesCombinatorGenerator/src/resources/CodeOptions.xlsx'

# Funkcja do odczytu danych z podanego zakresu w arkuszu
def read_excel(sheet_name, column_range):
    workbook = openpyxl.load_workbook(EXCEL_PATH)
    sheet = workbook[sheet_name]

    data = []
    for row in column_range:
        row_data = []
        for col in row:
            cell_value = sheet[col].value
            row_data.append(cell_value)
        data.append(row_data)
    return data

# Metoda dla Bolts
@app.route('/read_bolts', methods=['GET'])
def read_bolts():
    column_range = [
        ('B70', 'B100'),
        ('D70', 'D100'),
        ('E70', 'E100')
    ]
    data = read_excel('Bolts', column_range)
    return jsonify(data)

# Metoda dla Nuts
@app.route('/read_nuts', methods=['GET'])
def read_nuts():
    column_range = [
        ('B70', 'B100'),
        ('D70', 'D100'),
        ('E70', 'E100')
    ]
    data = read_excel('Nuts', column_range)
    return jsonify(data)

# Metoda dla Washers
@app.route('/read_washers', methods=['GET'])
def read_washers():
    column_range = [
        ('B70', 'B100'),
        ('D70', 'D100'),
        ('E70', 'E100')
    ]
    data = read_excel('Washers', column_range)
    return jsonify(data)

if __name__ == '__main__':
    app.run(debug=True, port=5000)