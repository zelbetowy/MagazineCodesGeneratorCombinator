import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { HotTable } from '@handsontable/react';
import 'handsontable/dist/handsontable.full.css';
import './styles/main-page.css';
import axios from 'axios';

const MainPage: React.FC = () => {
    const [numberOfTables, setNumberOfTables] = useState<number>(3); // Domyœlnie 3 tabele
    const [tableData, setTableData] = useState<string[][][]>(
        Array(3).fill([['', '', ''], ['', '', ''], ['', '', '']])
    ); // Domyœlnie 3 puste tabele

    // Funkcja do wys³ania danych tabel w formacie JSON
    const sendTableData = () => {
        // Sprawdzamy, czy w tabelach s¹ dane (czy s¹ puste)
        const isDataPresent = tableData.some(table => table.flat().some(cell => cell !== ''));

        if (!isDataPresent) {
            alert('W tabelach nie ma danych do wys³ania!');
            return;
        }

        // Tworzenie dynamicznego JSON-a
        const columnRanges = tableData.reduce((acc, table, index) => {
            acc[`range${index + 1}`] = table;
            return acc;
        }, {});

        axios.post('http://localhost:7070/send_data', columnRanges)
            .then(response => {
                console.log('Dane zosta³y pomyœlnie wys³ane:', response.data);
            })
            .catch(error => {
                console.error('B³¹d przy wysy³aniu danych:', error);
            });
    };

    return (
        <div className="main-page">
            <h1>Panel do wklejania danych</h1>
            <label htmlFor="numberOfTables">Wybierz liczbê cz³onów:</label>
            <select
                id="numberOfTables"
                value={numberOfTables}
                onChange={(e) => {
                    const newTableCount = Number(e.target.value);
                    setNumberOfTables(newTableCount);
                    setTableData(Array(newTableCount).fill([['', '', ''], ['', '', ''], ['', '', '']]));
                }}
            >
                <option value={2}>2</option>
                <option value={3}>3</option>
                <option value={4}>4</option>
                <option value={5}>5</option>
                <option value={6}>6</option>
            </select>

            {[...Array(numberOfTables)].map((_, index) => (
                <div key={index} className="data-table">
                    <h2>Table {index + 1}</h2>
                    <HotTable
                        data={tableData[index]}
                        colHeaders={true}
                        rowHeaders={true}
                        licenseKey="non-commercial-and-evaluation"
                        afterChange={(changes) => {
                            if (changes) {
                                const newData = [...tableData];
                                newData[index] = changes.map(change => change[3]); // Aktualizacja danych
                                setTableData(newData);
                            }
                        }}
                    />
                </div>
            ))}

            <div className="Button2">
                <button onClick={sendTableData}>Wyœlij dane</button>
                <Link to="/">
                    <button>Powrót do strony g³ównej</button>
                </Link>
            </div>
        </div>
    );
};

export default MainPage;