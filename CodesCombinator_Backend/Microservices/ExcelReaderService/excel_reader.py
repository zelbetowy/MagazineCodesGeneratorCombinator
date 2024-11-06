import openpyxl
import os
import signal
import sys
from flask import Flask, jsonify

app = Flask(__name__)

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
EXCEL_PATH = os.path.join(BASE_DIR, 'CodeOptions.xlsx')
CONFIG_PATH = os.path.join(BASE_DIR, 'Excel_config.txt')
LOCK_FILE = os.path.join(BASE_DIR, 'excel_reader.lock')  # Plik flagi

# Funkcja do wczytywania konfiguracji z pliku
def load_config():
    config = {}
    with open(CONFIG_PATH, 'r') as config_file:
        for line in config_file:
            key, value = line.strip().split('=')
            if ',' in value:
                config[key] = value.split(',')
            else:
                config[key] = int(value) if value.isdigit() else value
    return config

# Wczytanie konfiguracji
config = load_config()

# Konfiguracja z pliku
column_letters_for_numbers = config['column_letters_for_numbers']
column_letters_for_names = config['column_letters_for_names']
COLUMN_START = config['COLUMN_START']
COLUMN_END = config['COLUMN_END']

NUM_RANGES = len(column_letters_for_numbers)

def generate_ranges_for_numbers():
    column_ranges = {}
    for i in range(NUM_RANGES):
        column_ranges[f'range{i + 1}'] = [f'{column_letters_for_numbers[i]}{j}' for j in range(COLUMN_START, COLUMN_END)]
    return column_ranges

def generate_ranges_for_names():
    column_ranges = {}
    for i in range(NUM_RANGES):
        column_ranges[f'range{i + 1}'] = [f'{column_letters_for_names[i]}{j}' for j in range(COLUMN_START, COLUMN_END)]
    return column_ranges

def read_excel(sheet_name, column_range):
    workbook = openpyxl.load_workbook(EXCEL_PATH)
    sheet = workbook[sheet_name]

    data = []
    for row in column_range:
        for col in row:
            cell_value = sheet[col].value

            # Usuwamy null'e z danych
            if cell_value is not None:
                data.append(cell_value)
    return data


# Metody Flask
@app.route('/read_bolts_numbersBlock', methods=['GET'])
def read_bolts_numbers():
    column_ranges = generate_ranges_for_numbers()
    result = {}
    for key, column_range in column_ranges.items():
        result[key] = read_excel('Bolts', [column_range])
    return jsonify(result)

@app.route('/read_bolts_namesBlock', methods=['GET'])
def read_bolts_names():
    column_ranges = generate_ranges_for_names()
    result = {}
    for key, column_range in column_ranges.items():
        result[key] = read_excel('Bolts', [column_range])
    return jsonify(result)

@app.route('/read_nuts_numbersBlock', methods=['GET'])
def read_nuts_numbers():
    column_ranges = generate_ranges_for_numbers()
    result = {}
    for key, column_range in column_ranges.items():
        result[key] = read_excel('Nuts', [column_range])
    return jsonify(result)

@app.route('/read_nuts_namesBlock', methods=['GET'])
def read_nuts_names():
    column_ranges = generate_ranges_for_names()
    result = {}
    for key, column_range in column_ranges.items():
        result[key] = read_excel('Nuts', [column_range])
    return jsonify(result)

@app.route('/read_washers_numbersBlock', methods=['GET'])
def read_washers_numbers():
    column_ranges = generate_ranges_for_numbers()
    result = {}
    for key, column_range in column_ranges.items():
        result[key] = read_excel('Washers', [column_range])
    return jsonify(result)

@app.route('/read_washers_namesBlock', methods=['GET'])
def read_washers_names():
    column_ranges = generate_ranges_for_names()
    result = {}
    for key, column_range in column_ranges.items():
        result[key] = read_excel('Washers', [column_range])
    return jsonify(result)

if __name__ == '__main__':
        print("Starting Python microservice...")
        app.run(debug=True, port=5000)
